package com.albert.mysql.messages;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.mysql.MybatisPlusApplication;
import com.albert.mysql.entity.MessagesDo;
import com.albert.mysql.service.IMessagesService;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/9/14
 */
@SpringBootTest(classes = MybatisPlusApplication.class)
@RunWith(SpringRunner.class)
public class TestMessages {

    @Autowired
    IMessagesService messagesService;

    /**
     * for udpate 阻塞状态
     */
    @SneakyThrows
    @Test
    public void lockQuery() {
        ThreadUtil.execAsync(() -> messagesService.lockQuery(6L));

        Thread.sleep(1000L);
        ThreadUtil.execAsync(() -> messagesService.lockQuery2(6L));

        Thread.sleep(15000L);
    }

    /**
     * for udpate 非阻塞状态
     */
    @SneakyThrows
    @Test
    public void lockQueryNoBlock() {
        ThreadUtil.execAsync(() -> messagesService.lockQueryNoBlock(6L));

        Thread.sleep(1000L);
        ThreadUtil.execAsync(() -> messagesService.lockQueryNoBlock2(6L));

        Thread.sleep(15000L);
    }


    @SneakyThrows
    @Test
    public void lock() {
        ThreadUtil.execAsync(() -> messagesService.lockMsg(6L));

        Thread.sleep(1000L);
        ThreadUtil.execAsync(() -> messagesService.lockMsg2(6L));

        Thread.sleep(15000L);
    }

    @Test
    public void list() {
        List<MessagesDo> list = messagesService.list();
        System.out.println(JSON.toJSONString(list));
    }


}
