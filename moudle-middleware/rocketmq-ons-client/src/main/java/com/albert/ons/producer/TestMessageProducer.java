package com.albert.ons.producer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.albert.ons.config.RocketMQProperties;
import com.albert.ons.rocketmq.RocketMQProducer;
import com.albert.ons.rocketmq.RocketMqUtil;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;

/**
 * @author Albert.Yang
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TestMessageProducer {

    private final RocketMQProducer rocketMQProducer;

    private final RocketMQProperties rocketMQProperties;

    /**
     * 上报需要延时检查的场所在线状态规则id
     *
     * @return
     */
    public boolean reportTestMsg(String msg, Long delayTimeMillis) {
        // 写入mq
        RocketMqUtil.sendDelayMessage(rocketMQProducer, rocketMQProperties.getTopicName(), rocketMQProperties.getTestTag(), msg, delayTimeMillis);
        // RocketMqUtil.sendMessage(rocketMQProducer, rocketMQProperties.getTopicName(), rocketMQProperties.getTestTag(), msg);
        return true;
    }

}
