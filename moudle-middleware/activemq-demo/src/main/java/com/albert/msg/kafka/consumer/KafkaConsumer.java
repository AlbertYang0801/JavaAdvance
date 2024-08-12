package com.albert.msg.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * kafka的消费者
 *
 * @author Albert
 * @date 2020/7/27 20:19
 */
//@Component
@Slf4j
public class KafkaConsumer {

    public static final String TOPIC_GROUP = "topic.group";

    /**
     * kafka监听器，监听指定的topic
     *
     * @param record
     * @param ack
     * @param topic
     */
    @KafkaListener(topics = {"${kafka.test.topic}"}, groupId = TOPIC_GROUP)
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("从{}队列消费到的数据为:{}",topic,msg);
            //表示消息已被处理
            ack.acknowledge();
        }
    }

    /**
     * 批量消费消息
     */
    @KafkaListener(topics ={"${kafka.test.topic}"},containerFactory="batchFactory")
    public void consumerBatch(List<ConsumerRecord<?, ?>> record){
        log.info("接收到消息数量：{}",record.size());
        record.forEach(data->{
            Optional<?> kafkaMessage = Optional.ofNullable(data.value());
            if (kafkaMessage.isPresent()) {
                Object message = kafkaMessage.get();
                log.info("从队列批量消费到的数据为:{}",message);
            }
        });
    }


}
