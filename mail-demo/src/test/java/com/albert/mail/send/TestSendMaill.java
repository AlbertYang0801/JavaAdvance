package com.albert.mail.send;

import com.albert.mail.TestApplication;
import com.albert.mail.utils.MailUtil;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Albert
 * @date 2020/11/2 20:01
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestSendMaill {

    @Test
    public void sendMailPaht(){
        String path = "/Users/yangjunwei/Desktop/滴滴电子发票.pdf";
        List<String> fileList = Lists.newArrayList();
        fileList.add(path);
        fileList.add(path);
        MailUtil.sendMail("albert",
                "测试邮件发送图片功能", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com,albert.yang@cloudwise.com", fileList);
    }


}
