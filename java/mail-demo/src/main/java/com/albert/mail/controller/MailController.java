package com.albert.mail.controller;

import com.albert.mail.common.MailUtilHandler;
import com.albert.mail.job.SendMailTask;
import com.albert.mail.utils.ConfUtil;
import com.albert.mail.utils.MailUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        MailUtil.sendMail("albert",
                "测试邮件发送功能", "这是一封测试邮件。",
                "18438049166@163.com,albert.yang@cloudwise.com", Lists.newArrayList());
        return "发送成功";
    }

    @GetMapping("/sendPic")
    public String sendMailPic(@RequestParam("path") String path) {
        log.info("准备发送的图片路径为:{}", path);
        List<String> fileList = Lists.newArrayList();
        fileList.add(path);
        fileList.add(path);
        MailUtil.sendMail("albert",
                "测试邮件发送图片功能", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com,albert.yang@cloudwise.com", fileList);
        return "发送成功";
    }

    @GetMapping("/time/send")
    public String timedSendMail(@RequestParam("timestamp") Long timestamp) {
        SendMailTask sendMailTask = new SendMailTask(
                "albert", "测试定时发送",
                "这是一封测试定时发送的邮件",
                "18438049166@163.com", Lists.newArrayList());
        //指定当前时间间隔之后执行
        long timeInterval = timestamp - LocalDateTimeUtils.getNowMillis();
        //向定时线程池添加任务
        MailUtilHandler.addSendMailTask(sendMailTask, timeInterval);
        return "发送成功";
    }


}
