package com.albert.mail.controller;

import com.albert.mail.utils.ConfUtil;
import com.albert.mail.utils.MailUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                confUtil.getUserName(),
                confUtil.getPassword(),
                confUtil.getHost(),
                confUtil.getPort(),
                confUtil.getProtocol(),
                confUtil.getIsSsl(),
                confUtil.getMailDebug()
        );
        sendMail.SendMail("albert",
                "测试邮件发送功能", "这是一封测试邮件。",
                "18438049166@163.com,albert.yang@cloudwise.com",Lists.newArrayList());
        return "发送成功";
    }

    @PostMapping("/sendpic")
    public String sendMailPic(@RequestParam("path") String path) {
        log.info("准备发送的图片路径为:{}",path);
        MailUtil sendMail = new MailUtil(
                confUtil.getUserName(),
                confUtil.getPassword(),
                confUtil.getHost(),
                confUtil.getPort(),
                confUtil.getProtocol(),
                confUtil.getIsSsl(),
                confUtil.getMailDebug()
        );
        List<String> fileList = Lists.newArrayList();
//        fileList.add(path);
//        fileList.add(path);
        sendMail.SendMail("albert",
                "测试邮件发送图片功能", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com,albert.yang@cloudwise.com",fileList);
        return "发送成功";
    }


}
