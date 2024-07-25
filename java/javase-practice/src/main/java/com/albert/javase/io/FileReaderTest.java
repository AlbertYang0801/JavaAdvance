package com.albert.javase.io;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 节点流（文件流)-FileReader
 * 是一种字符输入流
 *
 * @author yangjunwei
 * @date 2021/9/7 5:49 下午
 */
public class FileReaderTest {

    /**
     * 测试FileReader的基本读取方法。
     * read()方法
     */
    @SneakyThrows
    @Test
    public void testRead() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        File file = new File(path);
        FileReader fileReader = null;
        try {
            //1.将文件加载到流对象
            fileReader = new FileReader(file);
            //2.循环读取文件内容，读取不到时值为-1
            while (fileReader.read() != -1) {
                System.out.println(fileReader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭流对象
            fileReader.close();
        }
    }

    /**
     * 测试FileReader的基本读取方法。
     * read(char cbuf[])方法
     */
    @Test
    public void testReadChars() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        File file = new File(path);
        try {
            //1.将文件加载到流对象
            FileReader fileReader = new FileReader(file);
            //2.创建一个空的字符数组(足够大)
            char[] chars = new char[1024];
            //3.将流对象中的数据读取到字符数组中
            fileReader.read(chars);
            //4.关闭流对象
            fileReader.close();
            String str = new String(chars);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试查询方法
     */
    @Test
    public void testReadCharsQuery() throws IOException {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        File file = new File(path);
        FileReader fileReader = null;
        try {
            //1.将文件加载到流对象
            fileReader = new FileReader(file);
            //2.创建一个空的字符数组
            char[] chars = new char[1024];
            //3.将流对象中的数据读取到字符数组中
            int len;
            fileReader.read(chars);
            while ((len = fileReader.read(chars)) != -1) {
                System.out.println("文件内容长度为：" + len);
                //方式一：
                //错误(若chars内容变化，则结果不准确)
//                for (int i = 0; i < chars.length; i++) {
//                    System.out.println(chars[i]);
//                }
                //正确写法
                for (int i = 0; i < len; i++) {
                    System.out.println(chars[i]);
                }
                //方式二：
                //错误
//                String str = new String(chars);
//                System.out.println(str);
                String str = new String(chars, 0, len);
                System.out.println(str);
            }
            String str = new String(chars);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流对象
            fileReader.close();
        }
    }


}
