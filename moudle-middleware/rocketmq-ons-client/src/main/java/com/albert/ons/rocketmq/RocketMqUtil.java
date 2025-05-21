package com.albert.ons.rocketmq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import org.springframework.util.SerializationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * mq  topic util
 *
 * @author linn
 */
@Slf4j
public class RocketMqUtil {

    /**
     * 发送消息
     */
    public static void sendMessage(RocketMQProducer rocketMQService, String topic, String tags, Object object) {
        SendResult sendResult = rocketMQService.sendMessage(new Message(topic, tags, SerializationUtils.serialize(object)));
        log.info("send message success. {}", sendResult.toString());
    }

    /**
     * 发送延时消息
     *
     * @param delayTimeMillis 时长(单位：毫秒),最大延迟时间为7天
     */
    public static void sendDelayMessage(RocketMQProducer rocketMQService, String topic, String tags, Object object, Long delayTimeMillis) {
        SendResult sendResult = rocketMQService.sendDelayMessage(new Message(topic, tags, SerializationUtils.serialize(object)), delayTimeMillis);
        log.info("send delay message success. {}", sendResult.toString());
    }

    /**
     * 单向发送
     */
    public static void sendOneWayMessage(RocketMQProducer rocketMQService, String topic, String tags, Object object) {
        rocketMQService.sendOneWayMessage(new Message(topic, tags, SerializationUtils.serialize(object)));
    }



}
