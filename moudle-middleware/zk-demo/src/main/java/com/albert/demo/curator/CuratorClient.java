package com.albert.demo.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yangjunwei
 * @date 2024-04-03
 */
@Component
public class CuratorClient {

    private CuratorFramework client;

    public CuratorFramework getInstance() {
        return client;
    }

    @PostConstruct
    public void initClient()  {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
    }


}
