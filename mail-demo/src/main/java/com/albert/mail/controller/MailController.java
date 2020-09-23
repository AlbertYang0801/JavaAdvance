package com.albert.mail.controller;

import com.albert.mail.utils.ConfUtil;
import com.albert.mail.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albert
 * @date 2020/9/23 14:32
 */
@RestController
@Slf4j
public class MailController {

    @Autowired
    ConfUtil confUtil;

    @PostMapping("/send")
    public String sendMail() {
        MailUtil sendMail = new MailUtil(
                confUtil.getAccount(),
                confUtil.getPassword(),
                confUtil.getHost(),
                confUtil.getPort(),
                confUtil.getProtocol()
        );
        sendMail.sendMsg("albert",
                "测试邮件发送功能", "这是一封测试邮件。",
                "18438049166@163.com,albert.yang@cloudwise.com");
        return "发送成功";
    }

    @PostMapping("/sendpic")
    public String sendMailPic(@RequestParam("path") String path) {
        log.info("准备发送的图片路径为:{}",path);
        MailUtil sendMail = new MailUtil(
                confUtil.getAccount(),
                confUtil.getPassword(),
                confUtil.getHost(),
                confUtil.getPort(),
                confUtil.getProtocol()
        );
        sendMail.sendFile("albert",
                "测试邮件发送图片功能", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com,albert.yang@cloudwise.com",path);
        return "发送成功";
    }


}
