package com.albert.mysql.service.impl;

import cn.hutool.json.JSONUtil;
import com.albert.mysql.entity.MessagesDo;
import com.albert.mysql.mapper.MessagesMapper;
import com.albert.mysql.service.IMessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-13
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, MessagesDo> implements IMessagesService {

    /**
     * 只查询 - 行锁
     * 第一个事务
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockQuery(Long id) {
        System.out.println("lock 1  start");
        MessagesDo messagesDo = this.getBaseMapper().lockQueryMessageDo(id);
        System.out.println("lock 1  " + JSONUtil.toJsonStr(messagesDo));
        Thread.sleep(10000L);
        System.out.println("lock 1  end");
        return messagesDo != null;
    }

    /**
     * 只查询-行锁
     * 第二个事务
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockQuery2(Long id) {
        Thread.sleep(1000L);
        System.out.println("lock 2 start");
        MessagesDo messagesDo = this.getBaseMapper().lockQueryMessageDo(id);
        System.out.println("lock 2  " + JSONUtil.toJsonStr(messagesDo));
        System.out.println("lock 2  end");
        return messagesDo != null;
    }

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockQueryNoBlock(Long id) {
        System.out.println("lock 1  start");
        MessagesDo messagesDo = this.getBaseMapper().lockQueryNoBolck(id);
        System.out.println("lock 1  " + JSONUtil.toJsonStr(messagesDo));
        Thread.sleep(10000L);
        System.out.println("lock 1  end");
        return messagesDo != null;
    }

    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockQueryNoBlock2(Long id) {
        Thread.sleep(1000L);
        System.out.println("lock 2 start");
        MessagesDo messagesDo = this.getBaseMapper().lockQueryNoBolck(id);
        System.out.println("lock 2  " + JSONUtil.toJsonStr(messagesDo));
        System.out.println("lock 2  end");
        return messagesDo != null;
    }

    /**
     * 查询+更新 第一个事务
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockMsg(Long id) {
        System.out.println("lockmsg 1  start");

        MessagesDo messagesDo = this.getBaseMapper().lockMessageDo(id);
        System.out.println("lockmsg 1  " + JSONUtil.toJsonStr(messagesDo));
        Thread.sleep(10000L);
        //当前事务更新状态
        this.getBaseMapper().updateStatus(id);
        System.out.println("lockmsg 1  end");
        return messagesDo != null;
    }

    /**
     * 查询+更新 第二个事务
     *
     * @param id
     * @return
     */
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockMsg2(Long id) {
        Thread.sleep(1000L);
        System.out.println("lockmsg 2  start");

        MessagesDo messagesDo = this.getBaseMapper().lockMessageDo(id);
        System.out.println("lockmsg 2  :" + JSONUtil.toJsonStr(messagesDo));
        //当前事务更新状态
        if (Objects.nonNull(messagesDo)) {
            this.getBaseMapper().updateStatus(id);
        }
        System.out.println("lockmsg 2  end");
        return messagesDo != null;
    }


}
