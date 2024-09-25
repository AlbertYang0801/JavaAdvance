package com.albert.custom.producer.impl;

import com.albert.custom.producer.ProducerClientTemplate;
import com.albert.message.db.annotations.ConditionalGlobalMessageOnRocketMq;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnRocketMq
//开启重试
@EnableRetry
@Slf4j
public class RocketMqProducerClient implements ProducerClientTemplate {

    @Autowired
    private RocketMQTemplate mqTemplate;

    @Override
    public String sendMessage(String topic, String msg) {
        Message message = new Message();
        message.setTopic(topic);
        message.setBody(msg.getBytes());
        // 发送普通消息
        SendResult sendResult = sendMessage(message);
        return Optional.ofNullable(sendResult).map(SendResult::getMsgId).orElse(StringUtils.EMPTY);
    }

    @Override
    public String sendDelayMessage(String topic, String msg, Long startDeliverTime) {
        Message message = new Message();
        message.setTopic(topic);
        message.setBody(msg.getBytes());
        message.setDelayTimeMs(startDeliverTime);
        // 发送延时消息
        SendResult sendResult = sendMessage(message);
        return Optional.ofNullable(sendResult).map(SendResult::getMsgId).orElse(StringUtils.EMPTY);
    }

    /**
     * 发送消息，支持重拾
     *
     * @param message 消息体
     */
    @Retryable(include = Exception.class, maxAttempts = 3)
    private SendResult sendMessage(Message message) {
        String msgObj = new String(message.getBody(), StandardCharsets.UTF_8);
        SendResult result = null;
        try {
            result = mqTemplate.getProducer().send(message);
            //打印详情日志
            log.info("rocketmq 发出消息 exit[OK],msgObj={},result={}", JSON.toJSONString(msgObj), result);
        } catch (Throwable t) {
            log.info("rocketmq 发出消息 exit[异常],msgObj={},result={}", JSON.toJSONString(msgObj), result);
            throw new RuntimeException(t);
        }
        return result;
    }


}
