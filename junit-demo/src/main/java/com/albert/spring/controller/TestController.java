package com.albert.spring.controller;

import com.albert.spring.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/7/18
 */
@RestController
public class TestController {

    @Autowired
    ITestService testService;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }


}
