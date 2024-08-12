package com.albert.rocketmq.consumer;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/4
 */
@Component
public class MyConsumer implements InitializingBean {


    @SneakyThrows
    public void test() {
        System.out.println("开启消费者");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PUSH_CONSUMER");
        consumer.setNamesrvAddr("10.10.102.83:9876");
        consumer.subscribe("TopicTest", "*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    String content = String.valueOf(msg.getBody());
                    int queueId = msg.getQueueId();
                    System.out.println("消费数据时间:" + LocalDateTime.now() + ";内容： " + content + ";queueId:" + queueId);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.test();
    }


}
