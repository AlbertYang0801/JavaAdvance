package com.albert.msg.activemq.controller;

import com.albert.msg.activemq.producer.ThreadProducer;
import com.albert.msg.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albert
 * @date 2020/7/27 20:33
 */
@RestController
@RequestMapping("/mq")
public class TestController {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @PostMapping("/sendmsg")
    public void sendMsg() {
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
