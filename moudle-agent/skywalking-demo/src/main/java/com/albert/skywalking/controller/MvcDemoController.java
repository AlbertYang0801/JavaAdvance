package com.albert.skywalking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringMVC测试
 * @author yangjunwei
 * @date 2024/8/19
 */
@RestController
@RequestMapping("/demo")
public class MvcDemoController {

    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello2")
    public String ignore() {
        return "ignore";
    }


}
