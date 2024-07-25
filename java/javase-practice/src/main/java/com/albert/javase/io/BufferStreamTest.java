package com.albert.javase.io;

import com.albert.utils.jackson.JsonUtil;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * 缓冲流的练习
 *
 * @author yangjunwei
 * @date 2021/9/8 4:08 下午
 */
public class BufferStreamTest {

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

    /**
     * 使用缓冲流实现图片加密
     */
    @Test
    public void encyPic() {
        String path = "src/main/java/com/albert/javase/io/file/hello.jpg";
        //加密图片
        String encyOutputPath = "src/main/java/com/albert/javase/io/file/ency.jpg";
        //解密图片
        String decryptOutputPath = "src/main/java/com/albert/javase/io/file/decrypt.jpg";

        //加密（对图片每个字节进行异或运算）
        encyPic(path, encyOutputPath, 10);
        //再次加密即可解密（对图片每个字节进行第二次异或运算，即可得到原字节）
        encyPic(encyOutputPath, decryptOutputPath, 10);

    }

    private void encyPic(String path, String outputPath, int password) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            //节点流
            fileInputStream = new FileInputStream(path);
            fileOutputStream = new FileOutputStream(outputPath);
            //缓冲流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int b;
            while ((b = bufferedInputStream.read()) != -1) {
                //将字节加密（字节b进行异或运算，解密时再进行一次异或运算即可得到b）
                //再次调用该方法即可得到原字节
                bufferedOutputStream.write(b ^ password);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭最外层流对象，对应节点流也会关闭
                if (bufferedInputStream != null) {
                    //带有缓冲区的流对象关闭，会在关闭流之前刷新缓冲区
                    bufferedInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 统计某个文本中，各个字符出现的次数
     * 并将字符统计结果输出到文本中
     */
    @Test
    public void countChar() {
        String path = "src/main/java/com/albert/javase/io/file/data.txt";
        String outputPath = "src/main/java/com/albert/javase/io/file/newdata.txt";
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        Map<Character, Integer> map = Maps.newHashMap();
        try {
            //节点流
            fileReader = new FileReader(path);
            fileWriter = new FileWriter(outputPath);
            //缓冲流
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
            int b;
            while ((b = bufferedReader.read()) != -1) {
                char cValue = (char) b;
                if (map.containsKey(cValue)) {
                    Integer value = map.get(cValue);
                    map.put(cValue, value + 1);
                } else {
                    map.put(cValue, 1);
                }
            }
            System.out.println(JsonUtil.toString(map));
            Set<Map.Entry<Character, Integer>> entries = map.entrySet();
            for (Map.Entry<Character, Integer> entry : entries) {
                Character key = entry.getKey();
                Integer value = entry.getValue();
                bufferedWriter.write(key+"="+value);
                bufferedWriter.write("\n");
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
