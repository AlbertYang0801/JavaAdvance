package com.albert.middleware.controller;

import com.albert.middleware.ProducerCli;
import com.albert.db.entiry.MessagesDo;
import com.albert.db.service.IMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@RestController
@RequestMapping("/mid")
public class MiddlewareController {

    @Resource
    private ProducerCli producerCli;

    @Autowired
    IMessagesService messagesService;

    @GetMapping("/send")
    public String send() {
        return producerCli.sendDelayMessage("merge-order", "这是一条测试消息", 10000L);
    }

    @GetMapping("/list")
    public List<MessagesDo> list() {
        return messagesService.list();
    }


}
