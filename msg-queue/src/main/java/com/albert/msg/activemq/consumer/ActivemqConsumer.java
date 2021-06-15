package com.albert.msg.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 简单的消费者
 * @author Albert
 * @date 2020/8/14 17:50
 */
@Component
public class ActivemqConsumer {

    /**
     * 监听队列，一个消费者
     */
    @JmsListener(destination = "${activemq.topoc.test}")
    public void receiveQueue(String msg) {
        System.out.println(msg+"消息已经消费了");
    }

    /**
     * 另一个消费者
     */
    @JmsListener(destination = "${activemq.topoc.test}")
    public void receiveOtherQueue(String msg) {
        System.out.println(msg+"消息已经消费了");
    }


}
