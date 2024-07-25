package com.albert.javase.io;

import org.junit.Test;

import java.io.*;
import java.util.Objects;

/**
 * 节点流（文件流)-FileWriter
 * 是一种字符输出流
 *
 * @author yangjunwei
 * @date 2021/9/8 9:55 上午
 */
public class FileWriteTest {

    /**
     * 测试文件写入
     */
    @Test
    public void testWrite() {
        //要写入的文件可以不存在，并不会报异常，在输出的过程会自动创建文件
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            //1.创建输出流（append可以指定覆盖原文件，还是追加原文件）
            fileWriter = new FileWriter(file, true);
            //2.写入数据
            fileWriter.write("测试插入数据\n");
            fileWriter.write("hello world\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fileWriter)) {
                try {
                    //3.关闭字符输出流
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 结合FileReader实现文件复制
     */
    @Test
    public void testCopyFile() {

        String read = "src/main/java/com/albert/javase/io/file/data.txt";
        String write = "src/main/java/com/albert/javase/io/file/newdata.txt";

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            //1.创建字符输入流、字符输出流
            fileReader = new FileReader(read);
            fileWriter = new FileWriter(write,false);
            //2.创建缓冲区
            char[] chars = new char[1024];
            int length;
            //3.循环写入数据
            while ((length=fileReader.read(chars))!=-1){
                fileWriter.write(chars,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
