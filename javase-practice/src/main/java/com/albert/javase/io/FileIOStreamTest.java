package com.albert.javase.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * * 节点流（文件流)-FileInputStream
 * * 是一种文本字节输入流
 *
 * @author yangjunwei
 * @date 2021/9/8 10:19 上午
 */
public class FileIOStreamTest {

    /**
     * 测试FileInputStream读取文本文件（可能出现中文乱码）,FileOutputStream 输出文件。
     * 原因：一个汉字对应2～4个字节，当缓冲区固定时（比如6），容易读取到半个汉字，就输出文件。
     * 解决办法：使用字符输入流
     */
    @Test
    public void testInput() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        String outputPath = "src/main/java/com/albert/javase/io/file/newdata.txt";

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            //1.创建字节输入流
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream(outputPath);
            //2.创建字节数组
            byte[] bytes = new byte[5];
            int length;
            //3.从输入流读取数据到缓冲区
            while ((length = fileInputStream.read(bytes)) != -1) {
                //4.从缓冲区输出
                fileOutputStream.write(bytes, 0, length);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //4.关闭字节输入流
                fileInputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使用FileInputStream和FileOutputStream进行图片（非文件）复制
     */
    @Test
    public void testIOPic() {
        String path = "src/main/java/com/albert/javase/io/file/hello.jpg";
        String outputPath = "src/main/java/com/albert/javase/io/file/newhello.jpg";

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //1.创建字节输入流和字节输出流
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream(outputPath);
            //2.创建字节数组
            byte[] bytes = new byte[5];
            int len;
            //3.循环（输入流-》字节数组-》输出流）
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
