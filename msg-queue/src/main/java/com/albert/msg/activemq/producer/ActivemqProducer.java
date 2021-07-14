package com.albert.msg.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * 简单的生产者
 * @author Albert
 * @date 2020/8/14 17:49
 */
@Component
@EnableScheduling
public class ActivemqProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${activemq.topoc.test}")
    private String topic;

    /**
     * 定时发送
     */
    @Scheduled(fixedDelay = 2000)
    public void send(){
        jmsMessagingTemplate.convertAndSend(topic, "生产者发送消息");
    }

    /**
     * 只发送一次消息
     * 创建5个线程发送5条消息
     */
    @Scheduled(initialDelay = 2000,fixedDelay = 50000)
    public void asynSend(){
        int num = 5;
        for(int i=0;i<num;i++){
            String msg = "这是第"+i+"条消息";
            ThreadProducer threadProducer = new ThreadProducer();
            threadProducer.setJmsMessagingTemplate(jmsMessagingTemplate);
            threadProducer.setMsg(msg);
            Thread thread = new Thread(threadProducer);
            thread.start();
        }
    }

    /**
     * 只发送一次消息
     * 创建5个线程发送5条消息
     */
    public void sendMsg(){
        int num = 5;
        for(int i=0;i<num;i++){
            String msg = "这是第"+i+"条消息";
            ThreadProducer threadProducer = new ThreadProducer();
            threadProducer.setJmsMessagingTemplate(jmsMessagingTemplate);
            threadProducer.setMsg(msg);
            Thread thread = new Thread(threadProducer);
            thread.start();
        }
    }


}
