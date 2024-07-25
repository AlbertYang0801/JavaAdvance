package com.albert.mysql.tran;

import com.albert.mysql.mapper.UserMapper;
import com.albert.mysql.model.po.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author yjw
 * @date 2024/4/12 0:12
 */
@Component
@Slf4j
public class TranUserService {

    @Autowired
    UserMapper userMapper;

//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
//    public void insertUserA() {
//        userMapper.insert(new UserDo().build("小A"));
//        insertUserB();
//    }
//
//    @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
//    public void insertUserB() {
//        userMapper.insert(new UserDo().build("小B"));
//        //回滚之后会继续向上抛出异常，虽然不是一个事务，但是两个SQL还是都会回滚
//        int i = 10 / 0;
//
////        try {
////            b();
////        } catch (Exception e) {
////            //强制回滚
////            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
////            log.error("抛出异常，强制回滚");
////        }
//    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertUserA() {
        userMapper.insert(new UserDo().build("小A"));

        boolean isSuccess = true;
        try {
            insertUserB();
        } catch (Exception ex) {
            isSuccess = false;
            log.error("insertUserB发生异常，不影响insertUserA的提交", ex);
        }

        if (isSuccess) {
            // 如果insertUserB成功，则提交insertUserA的事务
        } else {
            // 如果insertUserB失败，可根据业务需求决定是否手动回滚insertUserA的事务
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertUserB() {
        userMapper.insert(new UserDo().build("小B"));
        int i = 10 / 0;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void callBack() {
        userMapper.insert(new UserDo().build("小B"));
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            //强制回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("抛出异常，强制回滚");
        }
    }

}

