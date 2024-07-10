package com.albert.rocketmq.tag;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 使用SQL过滤需要在broker.conf中增加配置 enablePropertyFilter=true。 这个属性默认是false。
 * 只有推模式的消费者可以使用SQL过滤，拉模式是用不了的。
 *
 * @author yangjunwei
 * @date 2024/7/4
 */
public class SqlFilterConsumer {

    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Don't forget to set enablePropertyFilter=true in broker
        consumer.subscribe("SqlFilterTest",
                MessageSelector.bySql("(TAGS is not null and TAGS in ('TagA', 'TagB'))" +
                        "and (a is not null and a between 0 and 3)"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }


}
