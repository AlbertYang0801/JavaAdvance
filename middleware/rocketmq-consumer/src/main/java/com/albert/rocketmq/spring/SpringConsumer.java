package com.albert.rocketmq.spring;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 注意下@RocketMQMessageListener这个注解的其他属性
 * consumeMode:有序消费还是并发消费
 * messageModel: 集群部通知还是广播通知
 *
 * @author yangjunwei
 * @date 2024/7/4
 */
@Component
@RocketMQMessageListener(consumerGroup = "MyConsumerGroup", topic = "TestTopic", consumeMode = ConsumeMode.ORDERLY)
public class SpringConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("Received message : " + message);
    }

}
