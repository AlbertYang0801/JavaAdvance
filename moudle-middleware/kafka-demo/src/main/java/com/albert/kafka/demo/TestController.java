package com.albert.kafka.demo;

import com.albert.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangjunwei
 * @date 2024/10/10
 */
@RestController
public class TestController {

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/sendTest")
    public void sendTest(@RequestParam("msg") String msg){
        kafkaProducer.send(msg,"aaa");
    }


}
