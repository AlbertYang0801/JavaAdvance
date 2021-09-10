package com.albert.javase.io;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.*;

/**
 * 转换流练习
 * @author yangjunwei
 * @date 2021/9/9 5:46 下午
 */
public class ConverStreamTest {

    /**
     * 测试InputStreamReader
     * 输入
     * 字节流=》字符流
     */
    @Test
    public void testInputStreamReader() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            char[] chars = new char[1024];
            int len;
            while ((len = inputStreamReader.read(chars)) != -1) {
                String content = new String(chars, 0, len);
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试OutputStreamWriter
     * 输出流
     * 字符流=》字节流
     */
    @SneakyThrows
    @Test
    public void testInputStreamReader2() {
        String path = "src/main/java/com/albert/javase/io/file/newdata.txt";
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            //将字节流转换为对应字符流输出
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK");
            outputStreamWriter.write("这是一行测试数据");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            outputStreamWriter.close();
        }

    }


}
