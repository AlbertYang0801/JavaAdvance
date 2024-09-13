package com.albert.middleware.producer;

import com.albert.middleware.ProducerCli;
import com.albert.middleware.annotations.ConditionalGlobalMessageOnRocketMq;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnRocketMq
@Slf4j
public class RocketMqProducerCli implements ProducerCli {

    /**
     * 发送延时消息，异常重试
     */
    @Autowired
    private RocketMQTemplate mqTemplate;

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
     * 同步发送普通消息
     *
     * @param message 消息体
     */
    private SendResult sendMessage(Message message) {
        String msgObj = new String(message.getBody(), StandardCharsets.UTF_8);
        SendResult result = null;
        try {
            result = mqTemplate.getProducer().send(message);
            //打印详情日志
            System.out.println(String.format("rocketmq 发出消息 exit[OK],msgObj=%s,result=%s", JSON.toJSONString(msgObj), result));

        } catch (Throwable t) {
            System.out.println(String.format("MyProducer.sendMessage exit[发送消息出现异常],topic=%s,msgObj=%s", message.getTopic(), JSON.toJSONString(msgObj)));
            throw new RuntimeException(t);
        }
        return result;
    }


}
