package com.albert.spring.statemachine.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 使用状态机管理不同订单的状态流转全流程
     */
    @GetMapping("testOrderStatusMachine")
    public void testOrderStatusMachine(){
        Long orderId1 = orderService.createOrder(new OrderDO());
        Long orderId2 = orderService.createOrder(new OrderDO());

        orderService.confirmOrder(orderId1);
        new Thread("客户线程"){
            @Override
            public void run() {
                orderService.deliver(orderId1);
                orderService.signOrder(orderId1);
                orderService.finishOrder(orderId1);
            }
        }.start();

        orderService.confirmOrder(orderId2);
        orderService.deliver(orderId2);
        orderService.signOrder(orderId2);
        orderService.finishOrder(orderId2);

        System.out.println("全部订单状态：" + orderService.listOrders());
    }



}