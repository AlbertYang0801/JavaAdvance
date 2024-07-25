package com.albert.spring.aware;

import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/19
 */
@Component
public class HelloAwareService {

    public String sayHello() {
        return "hello";
    }

}
