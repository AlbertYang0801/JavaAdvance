package com.albert.spring.entity.enums;

/**
 * 订单状态
 *
 * @author yangjunwei
 * @date 2024/8/22
 */
public enum OrderStatusEnum {

    /**
     * 待提交
     */
    DRAFT,
    /**
     * 待出库
     */
    SUBMITTED,
    /**
     * 已出库
     */
    DELIVERING,
    /**
     * 已签收
     */
    SIGNED,
    /**
     * 已完成
     */
    FINISHED;


}
