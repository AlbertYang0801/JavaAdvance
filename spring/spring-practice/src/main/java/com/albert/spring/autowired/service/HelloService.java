package com.albert.spring.autowired.service;

import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/17
 */
@Component
public class HelloService {

    public String sayHello(String name) {
        return "HelloA " + name;
    }

}
