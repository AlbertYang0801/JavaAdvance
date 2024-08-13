package com.albert.javase.test;

import com.albert.javase.test.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjw
 * @date 2024/7/31 22:18
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    ITestService testService;

    @GetMapping("/add")
    public void addOrder(@RequestParam String name){
        testService.buy(name);
    }


}
