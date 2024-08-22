package com.albert.spring.state;

/**
 * 状态上下文，维护当前状态对象
 * @author yangjunwei
 * @date 2024/8/22
 */
public class OrderContext {

    OrderState orderState;

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void handlerOrder(){
        orderState.handlerOrder();
    }



}
