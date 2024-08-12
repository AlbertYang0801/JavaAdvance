package com.albert.mysql.tran;

import com.albert.mysql.model.entity.OrderInfoDo;
import com.albert.mysql.service.IOrderService;
import com.albert.mysql.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangjunwei
 * @date 2024/8/6
 */
@Component
public class TranOrderService {

    @Autowired
    IOrderService orderService;
    @Autowired
    IUserService userService;
    @Autowired
    TranUserService tranUserService;

    /**
     * 测试本地方法调用
     * this调用，目标方法的事务不会生效
     * 没有走代理后的事务类
     */
    public void testLocal() {
        //调用一个事务方法不会回滚
        this.createTranOrder();
    }

    public void testRequiredNew(){
        insertOrderA();
        insertOrderB();
    }

    @Transactional
    public void createTranOrder() {
        OrderInfoDo orderInfoDo = OrderInfoDo.build();
        orderService.save(orderInfoDo);
        int i = 10 / 0;
    }

    /**
     * REQUIRED 默认事务传播行为
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertOrderA() {
        orderService.save(OrderInfoDo.build());
        //
        ////独立事务
        //insertOrderB();

        //整个A方法回滚，同样会回滚掉B的事务
        int i = 10/0;
    }

    /**
     * REQUIRES_NEW 以新的独立事务运行
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertOrderB() {
        orderService.save(OrderInfoDo.build());
    }

    /**
     * 测试A事务里面的异常，会不会影响B事务
     */
    public void createUserOrder(){
        //不影响A正常写入
        userService.insertUserNoException("AAAAA");
        //订单 独立事务 抛异常
        orderService.insertOrderException();

        //抛出异常，不执行
        userService.insertUserNoException("BBBB");
    }

    /**
     * 测试A事务里面的异常，会不会影响B事务
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void createUserOrderTx(){
        //触发大事务回滚，全部回滚
        userService.insertUserNoException("AAAAA");
        //订单 独立事务 抛异常
        orderService.insertOrderException();
    }


}
