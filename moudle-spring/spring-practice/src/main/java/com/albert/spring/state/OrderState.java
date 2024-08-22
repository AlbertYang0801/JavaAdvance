package com.albert.spring.state;

/**
 * 状态模式：将行为与状态相绑定
 * @author yangjunwei
 * @date 2024/8/22
 */
public interface OrderState {

    /**
     * 处理订单
     */
    void handlerOrder();


}
