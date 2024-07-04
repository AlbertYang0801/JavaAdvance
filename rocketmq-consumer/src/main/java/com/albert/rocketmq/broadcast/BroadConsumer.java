package com.albert.rocketmq.broadcast;

import com.albert.utils.localdatetime.LocalDateTimeUtils;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/4
 */
@Component
public class BroadConsumer implements InitializingBean {

    @SneakyThrows
    public void testB() {
        System.out.println("开启消费者");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PUSH_CONSUMER-A");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setNamesrvAddr("10.10.102.83:9876");
        consumer.subscribe("OrderTopicTest", "*");
        //接收广播消息
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String content = String.valueOf(msg.getBody());
                    int queueId = msg.getQueueId();
                    System.out.println("消费数据时间:" + LocalDateTimeUtils.getNow() + ";内容： " + content + ";queueId:" + queueId);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.testB();
    }


}
