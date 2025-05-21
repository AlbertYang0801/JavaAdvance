package com.albert.ons.rocketmq;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.springframework.util.SerializationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者监听
 * @author Albert.Yang
 *
 */
@Slf4j
public abstract class AbstractMessageListener<T> implements MessageListener {

    public abstract void handle(T body);

    @SuppressWarnings("unchecked")
    @Override
    public Action consume(Message message, ConsumeContext context) {
        log.info("接收消息:[topic: {}, tag: {}, msgId: {}, startDeliverTime: {}]", message.getTopic(), message.getTag(), message.getMsgID(), message.getStartDeliverTime());
        try {
            handle((T) SerializationUtils.deserialize(message.getBody()));
            return Action.CommitMessage;
        } catch (Exception e) {
            log.warn("handle message fail, requeue it. message id:{}", message.getMsgID(), e);
            return Action.ReconsumeLater;
        }
    }


}
