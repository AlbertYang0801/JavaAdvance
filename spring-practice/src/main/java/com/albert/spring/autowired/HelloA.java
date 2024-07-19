package com.albert.spring.autowired;

import com.albert.spring.autowired.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean依赖注入 - 构造方法
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
@Component
public class HelloA extends AbstractHello {

    /**
     * 构造方法注入HelloService
     *
     * @param helloService
     */
    public HelloA(@Autowired HelloService helloService) {
        this.helloService = helloService;
    }


}
