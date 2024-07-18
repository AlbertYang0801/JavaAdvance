package com.albert.junit.autowired;

import com.albert.junit.autowired.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean依赖注入 - set方法注入
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
@Component
public class HelloC extends AbstractHello {

    /**
     * set方法注入
     *
     * @param helloService
     */
    @Autowired
    public HelloC setHelloService(HelloService helloService) {
        this.helloService = helloService;
        return this;
    }


}
