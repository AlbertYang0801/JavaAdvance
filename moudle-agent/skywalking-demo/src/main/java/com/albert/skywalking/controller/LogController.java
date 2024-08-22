package com.albert.skywalking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/8/19
 */
@RestController
@RequestMapping("/log")
public class LogController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/logback")
    public String echo() {
        logger.info("测试日志");
        return "logback";
    }


}
