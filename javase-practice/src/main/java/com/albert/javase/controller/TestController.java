package com.albert.javase.controller;

import com.albert.javase.config.AlertManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2023/2/22 3:12 下午
 */
@RestController
public class TestController {

    @Autowired
    AlertManagerConfig alertManagerConfig;

    @GetMapping("/test")
    public AlertManagerConfig test(){
        return alertManagerConfig;
    }


}
