package com.albert.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }

    /**
     * 使用restTemplate调用
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
