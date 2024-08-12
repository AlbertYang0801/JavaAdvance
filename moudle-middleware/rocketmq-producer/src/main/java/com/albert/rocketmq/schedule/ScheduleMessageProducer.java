package com.albert.rocketmq.schedule;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * 延时消息
 *
 * @author yangjunwei
 * @date 2024/7/4
 */
public class ScheduleMessageProducer {

    private static String nameServer = "10.10.102.83:9876";

    @Test
    @SneakyThrows
    public void testSend() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("schedule-producer");
        defaultMQProducer.setNamesrvAddr(nameServer);
        defaultMQProducer.start();
        int totalMsgToSend = 100;
        for (int i = 0; i < totalMsgToSend; i++) {
            Message message = new Message("scheduleTopic", null, "test", ("hello schedule msg" + i).getBytes());
            //指定延时等级
            //1 = 1s
            //2 = 5s
            //3 = 10s
            message.setDelayTimeLevel(2);
            defaultMQProducer.send(message);
            System.out.println("发送消息时间：" + LocalDateTime.now());
        }
        defaultMQProducer.shutdown();
    }


}
