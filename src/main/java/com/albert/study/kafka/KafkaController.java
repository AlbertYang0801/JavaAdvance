package com.albert.study.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albert
 * @date 2020/7/27 20:33
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/kafka/sendmsg")
    public void sendMsgToKafka() {
        kafkaProducer.send("测试生产者发送消息");
    }


}
