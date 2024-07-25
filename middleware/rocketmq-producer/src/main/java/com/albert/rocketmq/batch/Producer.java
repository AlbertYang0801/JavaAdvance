package com.albert.rocketmq.batch;

import com.albert.rocketmq.contants.MqContants;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/4
 */
public class Producer {

    /**
     * 批量消息
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("BatchProducerGroupName");
        producer.setNamesrvAddr(MqContants.NAMESERVER_ADDR);
        producer.start();

        //If you just send messages of no more than 1MiB at a time, it is easy to use batch
        //Messages of the same batch should have: same topic, same waitStoreMsgOK and no schedule support
        String topic = "TopicTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "Tag", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "Tag", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "Tag", "OrderID003", "Hello world 2".getBytes()));
        producer.send(messages);
        System.out.println("send success");
        producer.shutdown();
    }



}
