package com.albert.spring.statemachine;

import com.albert.spring.entity.enums.OrderStatusEnum;
import com.albert.spring.entity.enums.OrderStatusOperateEventEnum;
import com.albert.spring.statemachine.order.OrderDO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
@Configuration
public class OrderPersist {

    /**
     * 状态机持久化配置
     * 可以配合DB和Cache进行持久化操作
     * @return
     */
    @Bean
    public DefaultStateMachinePersister<OrderStatusEnum, OrderStatusOperateEventEnum, OrderDO> stateMachinePersister() {
        //本地内存模拟持久化
        Map<OrderDO, StateMachineContext<OrderStatusEnum, OrderStatusOperateEventEnum>> map = new HashMap();
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatusEnum, OrderStatusOperateEventEnum, OrderDO>() {
            /**
             * 持久化操作
             * @param stateMachineContext 状态和事件对应关系 context
             * @param orderDO
             * @throws Exception
             */
            @Override
            public void write(StateMachineContext<OrderStatusEnum, OrderStatusOperateEventEnum> stateMachineContext, OrderDO orderDO) throws Exception {
                map.put(orderDO,stateMachineContext);
            }

            /**
             * 从持久层读取订单状态信息
             * @param orderDO
             * @return
             * @throws Exception
             */
            @Override
            public StateMachineContext<OrderStatusEnum, OrderStatusOperateEventEnum> read(OrderDO orderDO) throws Exception {
                //读取order的状态信息
                return map.get(orderDO);
            }
        });
    }


}

