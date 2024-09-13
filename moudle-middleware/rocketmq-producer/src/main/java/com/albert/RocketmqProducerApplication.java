package com.albert;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author Albert Yang
 */
@SpringBootApplication
public class RocketmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqProducerApplication.class, args);
    }

}
