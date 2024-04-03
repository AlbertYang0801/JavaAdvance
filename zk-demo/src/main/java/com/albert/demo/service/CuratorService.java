package com.albert.demo.service;

import com.albert.demo.curator.CuratorClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2024-04-03
 */
@Component
@Slf4j
public class CuratorService {

    private String ORDER_KEY = "order_kd";

    @Autowired
    CuratorClient curatorClient;

    @SneakyThrows
    public void create(int num) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/" + ORDER_KEY);
        try {
            if (lock.acquire(30, TimeUnit.SECONDS)) {
                log.info("{} get lock", num);
                Thread.sleep(600);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("{} release lock", num);
            lock.release();
            client.close();
        }
    }


}
