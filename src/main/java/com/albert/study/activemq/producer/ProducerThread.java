package com.albert.study.activemq.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * 开启新线程发送消息
 * 不占用主线程资源
 */
public class ProducerThread extends Thread  {

    @Value("${activemq.topoc.test}")
    private String topic;

    private JmsMessagingTemplate jmsMessagingTemplate;
    private  String account = "";
    private String json ="";
    @Override
    public void run() {
        jmsMessagingTemplate.convertAndSend(topic,json);

    }

    public JmsMessagingTemplate getJmsMessagingTemplate() {
        return jmsMessagingTemplate;
    }

    public void setJmsMessagingTemplate(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
