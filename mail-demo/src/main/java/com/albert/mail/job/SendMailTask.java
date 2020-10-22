package com.albert.mail.job;

import com.albert.mail.common.MailUtilHandler;
import com.albert.mail.utils.ConfUtil;
import com.albert.mail.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 发送邮件任务，使用线程
 *
 * @author Albert
 * @date 2020/10/20 10:29
 */
@Slf4j
public class SendMailTask implements Runnable {

    @Autowired
    ConfUtil confUtil;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 邮件接收方
     */
    private String receiver;

    /**
     * 附件路径列表
     */
    private List<String> pathList;


    public SendMailTask() {
    }

    public SendMailTask(String sender, String subject, String content, String receiver, List<String> pathList) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.receiver = receiver;
        this.pathList = pathList;
    }

    @Override
    public void run() {
        log.info("开始执行定时发送的任务");
        //发送邮件
        MailUtil.sendMail(sender,subject,content,receiver, pathList);
        log.info("执行定时发送任务结束");
    }


}
