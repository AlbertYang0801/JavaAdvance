package com.albert.study.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 简单的消费者
 * @author Albert
 * @date 2020/8/14 17:50
 */
//@Component
public class ActivemqConsumer {

    @JmsListener(destination = "${activemq.topoc.test}")
    public void receiveQueue(String consumer) {
        System.out.println(consumer+"消息已经消费了");
    }

}
