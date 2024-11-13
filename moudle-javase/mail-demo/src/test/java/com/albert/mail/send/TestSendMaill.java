package com.albert.mail.send;

import cn.hutool.core.io.FileUtil;
import com.albert.mail.TestApplication;
import com.albert.mail.common.MailUtilHandler;
import com.albert.mail.job.SendMailTask;
import com.albert.mail.utils.MailUtil;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
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
                "18438049166@163.com", Lists.newArrayList());
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


    @SneakyThrows
    @Test
    public void sendExcel(){
        // 创建Excel文件
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Hello, World!");

        if(FileUtil.isEmpty(new File("file"))){
            FileUtil.mkdir(new File("file"));
        }
        // 写入Excel文件
        FileOutputStream fos = new FileOutputStream("file/example.xlsx");
        workbook.write(fos);
        workbook.close();
        fos.close();

        String path = "file/example.xlsx";
        List<String> fileList = Lists.newArrayList();
        fileList.add(path);
        fileList.add(path);
        MailUtil.sendMail("albert",
                "测试附件", "这是一封测试邮件,包含图片文件。",
                "18438049166@163.com", fileList);
    }



}
