package com.albert.spring.aop.controller;

import com.albert.spring.aop.AuditLogAspect;
import com.albert.spring.aop.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjw
 * @date 2024/3/28 0:22
 */
@RequestMapping("/system")
@RestController
@Slf4j
public class SystemController {

    @Autowired
    SystemService systemService;

    @GetMapping("/add")
    @AuditLogAspect(oper = "add", methodName = "addSystem")
    public void add() {
        systemService.addSystem();
    }



}

