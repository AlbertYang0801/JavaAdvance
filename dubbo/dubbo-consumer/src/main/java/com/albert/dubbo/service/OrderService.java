package com.albert.dubbo.service;

import com.albert.dubbo.bean.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@Service
public class OrderService {

    @Resource
    private RestTemplate restTemplate;

    @DubboReference
    private UserService userService;

    public String createOrderByRest() {
        User user = restTemplate.getForObject("http://localhost:8082/user/1 ", User.class);
        System.out.println("创建订单");
        return user.toString() + " succeeded in creating the order";
    }

    public String createOrderByDubbo() {
        User user = userService.getUser("1");
        System.out.println("创建订单");
        return user.toString() + " succeeded in creating the order";
    }





}
