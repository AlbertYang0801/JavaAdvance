package com.albert.spring.aop.controller;

import com.albert.spring.aop.AuditLogAspect;
import com.albert.spring.aop.log.LogOperation;
import com.albert.spring.aop.service.SystemService;
import com.albert.spring.entity.req.SystemRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/update/abc")
    @LogOperation(value = "updateSystem")
    public SystemRequest update(@RequestBody SystemRequest systemRequest) {
        systemService.addSystem();
        return systemRequest;
    }

}

