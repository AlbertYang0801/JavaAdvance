package com.albert.mysql.service;

import com.albert.mysql.model.entity.OrderInfoDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Albert
 * @date 2021/4/19 下午4:03
 */
public interface IOrderService extends IService<OrderInfoDo> {

    void insertOrderNoException();

    void insertOrderException();


}
