package com.albert.rocketmq.spring.controller;

import com.albert.rocketmq.spring.SpringProducer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：楼兰
 * @description:
 **/
@RestController
@RequestMapping("/MQTest")
public class MQTestController {

    private final String topic = "TestTopic";
    @Resource
    private SpringProducer producer;

    @RequestMapping("/sendMessage")
    public String sendMessage(String message) {
        producer.sendMessage(topic, message);
        return "消息发送完成";
    }

    //这个发送事务消息的例子中有很多问题，需要注意下。
    @RequestMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }
}