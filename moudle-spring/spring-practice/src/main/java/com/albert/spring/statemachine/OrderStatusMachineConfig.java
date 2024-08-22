package com.albert.spring.statemachine;

import com.albert.spring.entity.enums.OrderStatusEnum;
import com.albert.spring.entity.enums.OrderStatusOperateEventEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置类
 * 配置状态，以及状态流转
 *
 * @author yangjunwei
 * @date 2024/8/22
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStatusMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderStatusOperateEventEnum> {

    /**
     * 设置状态机状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderStatusOperateEventEnum> states) throws Exception {
        //初始状态、结束状态
        //状态机所有状态
        states.withStates().initial(OrderStatusEnum.DRAFT)
                .end(OrderStatusEnum.FINISHED)
                .states(EnumSet.allOf(OrderStatusEnum.class));
    }

    /**
     * 设置状态机与订单状态操作事件绑定
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderStatusOperateEventEnum> transitions) throws Exception {
        //配置状态流转，和状态对应的事件
        //source->target event
        transitions.withExternal()
                .source(OrderStatusEnum.DRAFT).target(OrderStatusEnum.SUBMITTED).event(OrderStatusOperateEventEnum.CONFIRMED)
                .and()
                .withExternal().source(OrderStatusEnum.SUBMITTED).target(OrderStatusEnum.DELIVERING).event(OrderStatusOperateEventEnum.DELIVERY)
                .and()
                .withExternal().source(OrderStatusEnum.DELIVERING).target(OrderStatusEnum.SIGNED).event(OrderStatusOperateEventEnum.RECEIVED)
                .and()
                .withExternal().source(OrderStatusEnum.SIGNED).target(OrderStatusEnum.FINISHED).event(OrderStatusOperateEventEnum.CONFIRMED_FINISH);
    }


}
