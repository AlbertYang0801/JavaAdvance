package com.albert.activemq.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * 开一个新线程发送消息
 * @author Albert
 * @date 2020/9/7 17:48
 */
public class ThreadProducer implements Runnable{

    @Value("${activemq.topoc.test}")
    private String topic;

    private JmsMessagingTemplate jmsMessagingTemplate;

    private String msg = "";


    @Override
    public void run() {
        jmsMessagingTemplate.convertAndSend(topic,msg);

    }

    public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
