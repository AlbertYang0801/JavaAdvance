package com.albert.spring.autowired;

import com.albert.spring.autowired.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean依赖注入 - autowired方法注入
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
@Component
public class HelloD {

    @Autowired
    HelloService helloService;

    //HelloD(@Value("spring.name") @NotNull String name){
    //
    //}




}
