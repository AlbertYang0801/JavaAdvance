package com.albert.spring.autowired;

import com.albert.spring.autowired.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/18
 */
@Component
public class Test {

    @Autowired
    private HelloA helloA;
    @Value("server.port")
    private String value;


    private HelloService helloService;
    private String port;

    /**
     * set方法注入
     *
     * @param helloService
     */
    @Autowired
    public Test setHelloService(HelloService helloService) {
        this.helloService = helloService;
        return this;
    }

    /**
     * @Value
     *
     * @param port
     */
    public void setHelloService(@Value("server.port") String port) {
        this.port = port;
    }


}
