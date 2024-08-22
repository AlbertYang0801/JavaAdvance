package com.albert.spring.entity.enums;

/**
 * 订单状态流转事件
 *
 * @author yangjunwei
 * @date 2024/8/22
 */
public enum OrderStatusOperateEventEnum {

    /**
     * 确认,已提交
     */
    CONFIRMED,
    /**
     * 发货
     */
    DELIVERY,
    /**
     * 签收
     */
    RECEIVED,
    /**
     * 完成
     */
    CONFIRMED_FINISH;


}
