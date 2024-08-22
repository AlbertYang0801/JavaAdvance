package com.albert.spring.statemachine.order;

import com.albert.spring.entity.enums.OrderStatusEnum;
import lombok.Data;

/**
 * @author yangjunwei
 * @date 2024/8/22
 */
@Data
public class OrderDO {

    private Long orderId;

    private OrderStatusEnum orderStatusEnum;

    private String orderNo;


}
