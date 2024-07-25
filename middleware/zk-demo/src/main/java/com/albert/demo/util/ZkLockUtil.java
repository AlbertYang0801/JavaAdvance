package com.albert.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @author yangjunwei
 */
@Slf4j
public class ZkLockUtil implements AutoCloseable {

    private final ZooKeeper zooKeeper;

    private String zNode;

    public ZkLockUtil() {
        ZooKeeper zooKeeperClient = null;
        try {
            zooKeeperClient = new ZooKeeper("localhost:2181", 100000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.zooKeeper = zooKeeperClient;
    }

    @Override
    public void close() throws Exception {
        zooKeeper.delete(zNode, -1);
    }

    public boolean getLock(String businessCode) {
        try {
            //判断根节点
            judgeRootPath(businessCode);

            //创建临时有序节点，/order/order_0000001
            zNode = zooKeeper.create("/" + businessCode + "/" + "_", businessCode.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);

            //序号最小的获取到锁
            List<String> childrens = zooKeeper.getChildren("/" + businessCode, false);
            Collections.sort(childrens);
            String minZnode = childrens.get(0);

            //创建的节点是最小节点，就获取锁
            if (zNode.endsWith(minZnode)) {
                return true;
            }

            //监听上一个节点的删除事件
            String preNode = minZnode;
            CountDownLatch countDownLatch = new CountDownLatch(1);

            for (String cruZnode : childrens) {
                if (zNode.endsWith(cruZnode)) {
                    //watch preZNode，当 preZNode 删除的时候触发
                    zooKeeper.exists("/" + businessCode + "/" + preNode, watchedEvent -> {
                        countDownLatch.countDown();
                    });
                } else {
                    preNode = cruZnode;
                }
            }

            //阻塞自身，让出锁
            synchronized (this) {
                countDownLatch.await();
            }

            //当被唤醒的时候，说明上个节点被删除了，此时获取到锁
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void judgeRootPath(String businessCode) {
        try {
            //判断根节点是否存在
            Stat exists = zooKeeper.exists("/" + businessCode, false);
            //节点不存在，首次创建
            if (Objects.isNull(exists)) {
                //创建根节点目录
                //永久节点
                zooKeeper.create("/" + businessCode, businessCode.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            log.error("zk judge root path error:{}",e.getMessage());
        }
    }


}
