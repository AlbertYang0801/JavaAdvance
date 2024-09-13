package com.albert.mysql.controller;

import com.albert.mysql.service.IMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/9/13
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    IMessagesService messagesService;

    @GetMapping("/list")
    public void list(){
        messagesService.list();
    }


}
