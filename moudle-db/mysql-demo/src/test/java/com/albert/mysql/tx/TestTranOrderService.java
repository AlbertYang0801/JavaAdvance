package com.albert.mysql.tx;

import com.albert.mysql.MysqlApplication;
import com.albert.mysql.tran.TranOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
@SpringBootTest(classes = MysqlApplication.class)
@RunWith(SpringRunner.class)
public class TestTranOrderService {

    @Resource
    TranOrderService tranOrderService;

    /**
     * this调用事务方法测试
     */
    @Test
    public void testLocal() {
        tranOrderService.testLocal();
    }

    /**
     * 调用的方法抛异常，导致回滚
     */
    @Test
    public void testUser() {
        tranOrderService.createTranOrder();
    }

    /**
     * 测试同一个方法内，事务抛异常，调用的方法 独立事务是否会回滚
     */
    @Test
    public void insertOrderA() {
        tranOrderService.insertOrderA();
    }

    /**
     * 无事务调用
     * 订单抛异常，会不会影响User
     */
    @Test
    public void testOrderExcption() {
        tranOrderService.createUserOrder();
    }

    /**
     * 在一个大事务里面
     * 订单抛异常，会不会影响User
     */
    @Test
    public void testOrderExcptionTx() {
        tranOrderService.createUserOrderTx();
    }




}
