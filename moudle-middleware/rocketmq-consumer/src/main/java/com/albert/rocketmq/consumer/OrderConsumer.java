package com.albert.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "global.rocketmq.consumer.enable", havingValue = "true")
@RocketMQMessageListener(topic = "order", consumerGroup = "order")
public class OrderConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("收到 order 消息：" + messageStr);
    }


}
