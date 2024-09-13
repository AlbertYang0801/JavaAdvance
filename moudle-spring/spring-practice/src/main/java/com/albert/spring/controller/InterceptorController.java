package com.albert.spring.controller;

import com.albert.spring.interceptor.GlobalSession;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/9/11
 */
@RestController
@RequestMapping("/interceptor")
public class InterceptorController {


    @GetMapping("/test")
    public String test(){
        System.out.println("请求进Controller");
        return GlobalSession.getUserName();
    }


}
