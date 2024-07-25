package com.albert.rocketmq.simple;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * pull模式
 * @author admin
 * @date 2024/6/25 20:50
 */
public class PullConsumer {

    private final static String NAME_SERVER = "10.10.102.83:9876";
    private static final Map<MessageQueue, Long> OFFSE_TABLE = new HashMap<MessageQueue, Long>();

    @SneakyThrows
    @Test
    public void pull() {
        //pull
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("PULL_CONSUMER");
        consumer.setNamesrvAddr(NAME_SERVER);
        consumer.start();
        Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("test");
        for (MessageQueue mq : mqs) {
            System.out.printf("Consume from the queue: %s%n", mq);
            //循环中断
            SINGLE_MQ:
            while (true) {
                try {
                    //拉取消息
                    PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, getMessageQueueOffset(mq), 32);
                    System.out.printf("%s%n", pullResult);
                    OFFSE_TABLE.put(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = OFFSE_TABLE.get(mq);
        if (offset != null)
            return offset;

        return 0;
    }




}
