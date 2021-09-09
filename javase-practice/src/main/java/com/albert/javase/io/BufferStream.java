package com.albert.javase.io;

import org.junit.Test;

import javax.annotation.processing.Filer;
import java.io.*;

/**
 * 缓冲流的练习
 *
 * @author yangjunwei
 * @date 2021/9/8 4:08 下午
 */
public class BufferStream {

    /**
     * 缓冲流复制非文本文件-字节缓冲流
     * BufferedInputStream和BufferedOutputStream
     */
    @Test
    public void testBufferIoCopyPic() {
        String path = "src/main/java/com/albert/javase/io/file/hello.jpg";
        String outputPath = "src/main/java/com/albert/javase/io/file/newhello.jpg";

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            //1.创建字节输入流和字节输出流
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream(outputPath);

            //2.创建对应的缓冲流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            //3.创建字节数组
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bufferedInputStream.read(bytes)) != -1) {
                //缓冲区写入
                bufferedOutputStream.write(bytes, 0, length);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
//                    bufferedOutputStream.flush();
                    //缓冲区的流对象在关闭流之前，会自动刷新缓冲区
                    bufferedOutputStream.close();
                }
                //关闭带缓冲区的流对象，会自动关闭节点流
//                fileInputStream.close();
//                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 缓冲流复制文本文件-字符缓冲流
     * BufferedReader 和 BufferedWriter
     */
    @Test
    public void testCopyText() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        String outputPath = "src/main/java/com/albert/javase/io/file/newdata.txt";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            //节点流
            fileReader = new FileReader(path);
            fileWriter = new FileWriter(outputPath);
            //缓冲流
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            char[] chars = new char[1024];
            int length;
            while ((length = bufferedReader.read(chars)) != -1) {
                bufferedWriter.write(chars, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭最外层流对象，对应节点流也会关闭
                if (bufferedReader != null) {
                    //带有缓冲区的流对象关闭，会在关闭流之前刷新缓冲区
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
