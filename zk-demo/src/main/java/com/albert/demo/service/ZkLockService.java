package com.albert.demo.service;

import com.albert.demo.util.ZkLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 */
@Slf4j
@Component
public class ZkLockService {

    ZkLockUtil zkLockUtil = new ZkLockUtil();

    private final String ORDER_KEY = "test1";

    public void createOrder(int num) {
        try {
            //获取分布式锁
            if (zkLockUtil.getLock(ORDER_KEY)) {
                log.info("{} 获取到锁", num);
                Thread.sleep(600);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                log.info("{} 释放锁", num);
                zkLockUtil.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
