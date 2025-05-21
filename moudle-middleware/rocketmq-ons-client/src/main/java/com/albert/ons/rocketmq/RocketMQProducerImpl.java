package com.albert.ons.rocketmq;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Properties;

/**
 * @author linn
 */
@Slf4j
@Component
public class RocketMQProducerImpl implements RocketMQProducer {

    /** 配置信息 */
    @Resource(name = "rocketProperties")
    private Properties properties;

    /** 普通消息、延时消息、定时消息的生成者 */
    @Resource
    private Producer producer;

    /** 顺序消息生产者 */
    @Resource
    private OrderProducer orderProducer;

    @Override
    public SendResult sendMessage(Message message) {
        return producer.send(message);
    }

    @Override
    public void sendMessage(Message message, SendCallback callback) {
        producer.sendAsync(message, callback);
    }

    @Override
    public void sendOneWayMessage(Message message) {
        producer.sendOneway(message);
    }

    @Override
    public SendResult sendOrderMessage(Message message, String shardingKey) {
        return orderProducer.send(message, shardingKey);
    }

    @Override
    public SendResult sendDelayMessage(Message message, Long delayTimeMillis) {
        message.setStartDeliverTime(System.currentTimeMillis() + delayTimeMillis);
        return producer.send(message);
    }

    @Override
    public SendResult sendDateMessage(Message message, Date date) {
        message.setStartDeliverTime(date.getTime());
        return producer.send(message);
    }

    @Override
    public SendResult sendTransactionMessage(Message message, LocalTransactionChecker checker, LocalTransactionExecuter executer, Object o) {
        TransactionProducer transactionProducer = ONSFactory.createTransactionProducer(properties, checker);
        transactionProducer.start();
        return transactionProducer.send(message, executer, o);
    }
}
