package com.albert.kafka.consumer;

import org.apache.kafka.common.TopicPartition;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 消费者操作API
 *
 * @author Albert
 * @date 2021/2/3 下午4:37
 */
public class CountKafkaConsumer {

    /**
     * 获取当前某个topic里面的数据总量
     *
     * @param topic      主题
     * @param brokerList kafka地址-》10.0.7.212:9092
     * @return 数据总数
     */
    public static long totalMessageCount(String topic, String brokerList) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", "htestGroup01");
        props.put("enable.auto.commit", "false");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
        //    List<TopicPartition> tps = Optional.ofNullable(consumer.partitionsFor(topic))
        //            .orElse(Collections.emptyList())
        //            .stream()
        //            .map(info -> new TopicPartition(info.topic(), info.partition()))
        //            .collect(Collectors.toList());
        //    //第一条消息偏移量
        //    Map<TopicPartition, Long> beginOffsets = consumer.beginningOffsets(tps);
        //    //最后一条消息偏移量
        //    Map<TopicPartition, Long> endOffsets = consumer.endOffsets(tps);
        //    return tps.stream().mapToLong(tp -> endOffsets.get(tp) - beginOffsets.get(tp)).sum();
        //}
        return 0L;
    }

    public static void main(String[] args) {
        while (true) {
            long test = CountKafkaConsumer.totalMessageCount("testalbert1", "10.0.7.212:9092");
            System.out.println(test);
        }
    }


}
