package com.albert.custom;

import com.albert.message.db.config.MessageMysqlAutoConfiguration;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Albert Yang
 */
@SpringBootApplication(exclude = {RocketMQAutoConfiguration.class, MessageMysqlAutoConfiguration.class})
public class CustomMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomMqProducerApplication.class, args);
    }


}
