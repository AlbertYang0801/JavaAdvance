package com.albert.spring.statemachine;

import com.albert.spring.entity.enums.OrderStatusEnum;
import com.albert.spring.entity.enums.OrderStatusOperateEventEnum;
import com.albert.spring.statemachine.order.OrderDO;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 状态机监听器
 * 可以监听不同状态的事件
 * @author yangjunwei
 * @date 2024/8/22
 */
@Component
@WithStateMachine(name = "orderStateMachine")
public class OrderStatusListener {

    @OnTransition(source = "DRAFT", target = "SUBMITTED")
    public boolean payTransition(Message<OrderStatusOperateEventEnum> message) {
        OrderDO order = (OrderDO) message.getHeaders().get("order");
        order.setOrderStatusEnum(OrderStatusEnum.SUBMITTED);
        System.out.println(String.format("出库订单[%s]确认，状态机信息：%s", order.getOrderNo(), message.getHeaders()));
        return true;
    }

    @OnTransition(source = "SUBMITTED", target = "DELIVERING")
    public boolean deliverTransition(Message<OrderStatusOperateEventEnum> message) {
        OrderDO order = (OrderDO) message.getHeaders().get("order");
        order.setOrderStatusEnum(OrderStatusEnum.DELIVERING);
        System.out.println(String.format("出库订单[%s]发货出库，状态机信息：%s", order.getOrderNo(), message.getHeaders()));
        return true;
    }

    @OnTransition(source = "DELIVERING", target = "SIGNED")
    public boolean receiveTransition(Message<OrderStatusOperateEventEnum> message){
        OrderDO order = (OrderDO) message.getHeaders().get("order");
        order.setOrderStatusEnum(OrderStatusEnum.SIGNED);
        System.out.println(String.format("出库订单[%s]签收，状态机信息：%s", order.getOrderNo(), message.getHeaders()));
        return true;
    }

    @OnTransition(source = "SIGNED", target = "FINISHED")
    public boolean finishTransition(Message<OrderStatusOperateEventEnum> message){
        OrderDO order = (OrderDO) message.getHeaders().get("order");
        order.setOrderStatusEnum(OrderStatusEnum.FINISHED);
        System.out.println(String.format("出库订单[%s]完成，状态机信息：%s", order.getOrderNo(), message.getHeaders()));
        return true;
    }



}
