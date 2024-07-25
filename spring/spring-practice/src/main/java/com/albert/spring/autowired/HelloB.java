package com.albert.spring.autowired;

import com.albert.spring.autowired.service.HelloService;

/**
 * Bean依赖注入 - 配置类注入
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
public class HelloB extends AbstractHello {

    private String name;

    public HelloB(HelloService helloService) {
        this.helloService = helloService;
    }

    public HelloB(HelloService helloService,String name) {
        this.helloService = helloService;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void init() {
        System.out.println("init");
    }

    public void destory(){
        System.out.println("destory");
    }



}
