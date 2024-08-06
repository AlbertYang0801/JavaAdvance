package com.albert.dubbo.controller;

import com.albert.dubbo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/createOrder")
    public String createOrder() {
        return orderService.createOrderByDubbo();
    }


}
