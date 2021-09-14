package com.albert.javase.io;

import org.junit.Test;

import java.io.*;

/**
 * 数据流的练习
 * 为了方便地操作Java语言的基本数据类型和String的数据，可以使用数据流。
 *
 * @author yangjunwei
 * @date 2021/9/13 10:05 上午
 */
public class DataStreamTest {

    /**
     * 将内存中的字符串，基本数据类型变量写入到文件中。
     */
    @Test
    public void testWrite() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        DataOutputStream dataOutputStream = null;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(path));
            dataOutputStream.writeUTF("我回来了");
            dataOutputStream.flush();
            dataOutputStream.writeInt(222);
            dataOutputStream.flush();
            dataOutputStream.writeBoolean(true);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 从文件中读取字符串、基本数据类型到内存中
     */
    @Test
    public void testRead() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        DataInputStream dataInputStream = null;
        try {
            dataInputStream=new DataInputStream(new FileInputStream(path));
            String content = dataInputStream.readUTF();
            int i = dataInputStream.readInt();
            boolean b = dataInputStream.readBoolean();
            System.out.println(content);
            System.out.println(i);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataInputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}
