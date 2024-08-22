package com.albert.spring.statemachine.order;

import com.albert.spring.entity.enums.OrderStatusEnum;
import com.albert.spring.entity.enums.OrderStatusOperateEventEnum;
import com.albert.spring.statemachine.StateEventUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
@Service
public class OrderService {

    private StateEventUtil stateEventUtil;

    private static final AtomicInteger ID_COUNTER = new AtomicInteger(0);

    private static final Map<Long, OrderDO> ORDER_MAP = new ConcurrentHashMap<>();

    /**
     * 创建新订单
     *
     * @param orderDO
     */

    public Long createOrder(OrderDO orderDO) {
        long orderId = ID_COUNTER.incrementAndGet();
        orderDO.setOrderId(orderId);
        orderDO.setOrderNo("OC20240306" + orderId);
        orderDO.setOrderStatusEnum(OrderStatusEnum.DRAFT);
        ORDER_MAP.put(orderId, orderDO);
        System.out.println(String.format("订单[%s]创建成功:", orderDO.getOrderNo()));
        return orderId;
    }

    /**
     * 确认订单
     *
     * @param orderId
     */

    public void confirmOrder(Long orderId) {
        OrderDO order = ORDER_MAP.get(orderId);
        System.out.println("确认订单，订单号：" + order.getOrderNo());
        Message message = MessageBuilder.withPayload(OrderStatusOperateEventEnum.CONFIRMED).setHeader("order", order).build();
        if (!stateEventUtil.sendEvent(message)) {
            System.out.println(" 确认订单失败, 状态异常，订单号：" + order.getOrderNo());
        }
    }

    /**
     * 订单发货
     *
     * @param orderId
     */

    public void deliver(Long orderId) {
        OrderDO order = ORDER_MAP.get(orderId);
        System.out.println("订单出库，订单号：" + order.getOrderNo());
        Message message = MessageBuilder.withPayload(OrderStatusOperateEventEnum.DELIVERY).setHeader("order", order).build();
        if (!stateEventUtil.sendEvent(message)) {
            System.out.println(" 订单出库失败, 状态异常，订单号：" + order.getOrderNo());
        }
    }

    /**
     * 签收订单
     *
     * @param orderId
     */

    public void signOrder(Long orderId) {
        OrderDO order = ORDER_MAP.get(orderId);
        System.out.println("订单签收，订单号：" + order.getOrderNo());
        Message message = MessageBuilder.withPayload(OrderStatusOperateEventEnum.RECEIVED).setHeader("order", order).build();
        if (!stateEventUtil.sendEvent(message)) {
            System.out.println(" 订单签收失败, 状态异常，订单号：" + order.getOrderNo());
        }
    }

    /**
     * 确认完成
     *
     * @param orderId
     */

    public void finishOrder(Long orderId) {
        OrderDO order = ORDER_MAP.get(orderId);
        System.out.println("订单完成，订单号：" + order.getOrderNo());
        Message message = MessageBuilder.withPayload(OrderStatusOperateEventEnum.CONFIRMED_FINISH).setHeader("order", order).build();
        if (!stateEventUtil.sendEvent(message)) {
            System.out.println(" 订单完成失败, 状态异常，订单号：" + order.getOrderNo());
        }
    }

    /**
     * 获取所有订单信息
     */

    public List<OrderDO> listOrders() {
        return new ArrayList<>(ORDER_MAP.values());
    }

    @Autowired
    public void setStateEventUtil(StateEventUtil stateEventUtil) {
        this.stateEventUtil = stateEventUtil;
    }


}