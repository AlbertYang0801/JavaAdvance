package com.albert.spring.state.impl;

import com.albert.spring.state.OrderState;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
public class OrderSignedState implements OrderState {
    @Override
    public void handlerOrder() {
        System.out.println("订单签收");
    }
}
