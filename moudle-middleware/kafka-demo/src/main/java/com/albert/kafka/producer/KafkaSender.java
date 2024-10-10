package com.albert.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

@Slf4j
public class KafkaSender {

    private static KafkaProducer<String, byte[]> producer;

    private final static String KAFKA_ADDRESS = "10.10.102.83:9404";

    static {
        try {
            // setup kafka producer
            Properties props = new Properties();
            props.put("bootstrap.servers", KAFKA_ADDRESS);
            props.put("acks", "1");
            props.put("retries", 10);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 99680035);
            props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 104857600);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
            //if(Boolean.valueOf(Configuration.KAFKA_AUTHENTICATION_SCRAM_SWITCH)){
            //    props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");//安全协议
            //    props.put(SaslConfigs.SASL_MECHANISM, Configuration.KAFKA_AUTHENTICATION_SCRAM_MECHANISM);//SASL mechanism配置
            //    props.put("sasl.jaas.config", Configuration.KAFKA_AUTHENTICATION_SCRAM_CONFIG);//授权信息
            //}
            producer = new KafkaProducer<String, byte[]>(props);
            log.info("using kafka address : " + KAFKA_ADDRESS);
        } catch (Throwable ex) {
            log.error("error happens when construct kafka producer.");
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public static void send(String topic, String key, byte[] data) {
        producer.send(new ProducerRecord<String, byte[]>(topic, key, data), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    log.error("exception happens when send data to kafka:", exception);
                } else if (metadata != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("susscessfully send data to kafka!" + metadata.topic());
                    }
                }
            }
        });
    }


}
