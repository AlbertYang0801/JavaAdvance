package com.albert.spring.state;

import com.albert.spring.state.impl.OrderOutboundState;
import com.albert.spring.state.impl.OrderSignedState;
import com.albert.spring.state.impl.OrderSubmitState;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
public class OrderStateTest {

    public static void main(String[] args) {
        OrderContext orderContext = new OrderContext();
        orderContext.setOrderState(new OrderSubmitState());
        orderContext.handlerOrder();

        orderContext.setOrderState(new OrderOutboundState());
        orderContext.handlerOrder();

        orderContext.setOrderState(new OrderSignedState());
        orderContext.handlerOrder();
    }


}
