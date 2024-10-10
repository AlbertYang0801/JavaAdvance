package com.albert.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * kafka的生产者
 * 回调函数
 *
 * @author Albert
 * @date 2020/7/27 20:19
 */
@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.test.topic}")
    private String TOPIC_TEST;

    /**
     * 发送消息
     */
    public void send(String msg) {
        log.info("准备发送到kafka的消息为：{}", msg);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, msg);

        //生产者对消息发送结果的处理
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败
                log.info("生产者发送消息失败，topic:{},错误信息为:{}", TOPIC_TEST, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //发送成功
                log.info("生产者发送消息成功，topic:{},返回信息为:{}", TOPIC_TEST, stringObjectSendResult.toString());
            }
        });
    }

    /**
     * 发送消息
     */
    public void send(String msg, String topic) {
        log.info("准备发送到kafka的消息为：{}", msg);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, msg);

        //生产者对消息发送结果的处理
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败
                log.info("生产者发送消息失败，topic:{},错误信息为:{}", topic, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //发送成功
                log.info("生产者发送消息成功，topic:{},返回信息为:{}", topic, stringObjectSendResult.toString());
            }
        });
    }




}
