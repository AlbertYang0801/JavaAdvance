package com.albert.rpc.consumer;

import com.albert.rpc.framework.ProxyFactory;
import com.albert.rpc.provider.api.HelloService;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class Consumer {

    public static void main(String[] args) {
        //获取代理类
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("albert");
        System.out.println(result);
    }


}
