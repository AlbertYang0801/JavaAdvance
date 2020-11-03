package com.albert.mail.send;

import com.albert.mail.TestApplication;
import com.albert.mail.common.MailUtilHandler;
import com.albert.mail.job.SendMailTask;
import com.albert.mail.utils.MailUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Albert
 * @date 2020/11/2 20:01
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestSendMaill {

    @Test
    public void sendMail() {
        MailUtil.sendMail("albert",
                "测试邮件发送功能", "这是一封测试邮件。",
                "18438049166@163.com,albert.yang@cloudwise.com", Lists.newArrayList());
    }

    @Test
    public void sendMailPath(){
        String path = "/Users/yangjunwei/Desktop/滴滴电子发票.pdf";
        List<String> fileList = Lists.newArrayList();
        fileList.add(path);
        fileList.add(path);
        MailUtil.sendMail("albert",
                "测试邮件发送图片功能", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com,albert.yang@cloudwise.com", fileList);
    }

    @SneakyThrows
    @Test
    public void timedSendMail() {
        SendMailTask sendMailTask = new SendMailTask(
                "albert", "测试定时发送",
                "这是一封测试定时发送的邮件",
                "18438049166@163.com", Lists.newArrayList());
        //指定当前时间间隔之后执行
        long timeInterval = 10000;
        //向定时线程池添加任务
        MailUtilHandler.addSendMailTask(sendMailTask, timeInterval);
        Thread.sleep(15000);
    }


}
