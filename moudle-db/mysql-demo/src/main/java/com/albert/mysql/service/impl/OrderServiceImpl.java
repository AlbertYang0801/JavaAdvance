package com.albert.mysql.service.impl;

import com.albert.mysql.mapper.OrderInfoMapper;
import com.albert.mysql.model.entity.OrderInfoDo;
import com.albert.mysql.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangjunwei
 * @date 2024/8/6
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfoDo> implements IOrderService {


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertOrderNoException() {
        this.save(OrderInfoDo.build());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertOrderException() {
        this.save(OrderInfoDo.build());
        int i = 10 / 0;
    }


}
