package com.albert.ons.listener;

import com.albert.ons.rocketmq.AbstractMessageListener;
import com.albert.ons.rocketmq.RocketMQMessageListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 终端状态上报数据消费
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(topic = "${aliyunmq.rocketmq.topic.name}", tags = "${aliyunmq.rocketmq.topic.tags.test}", isCluster = true)
public class TestMsgListener extends AbstractMessageListener<String> {

    @Override
    public void handle(String message) {
        log.info("receive test message : {}", message);
        System.out.println(1+1);
    }

}
