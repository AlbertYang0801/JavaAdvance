package com.albert.javase.io;

import com.albert.utils.jackson.JsonUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;

/**
 * File类相关操作练习
 *
 * @author yangjunwei
 * @date 2021/9/7 10:00 上午
 */
public class FileTest {


    /**
     * 测试分隔符
     * windows：\\
     * unix：/
     */
    @Test
    public void testSeparator() {
        //file类提供的分隔符常量（根据操作系统动态提供分隔符）
        String separator = File.separator;
        System.out.println("分隔符结果为：" + separator);
    }


    @SneakyThrows
    @Test
    public void filePath() {
        //1.绝对路径
        File file = new File("/Users/yangjunwei/IdeaProjects/JavaAdvanced/file-demo/file/");
        System.out.println(file);///Users/yangjunwei/IdeaProjects/JavaAdvanced/file-demo/file
        System.out.println(JsonUtil.toString(file.list()));//["666.xls","CheckTest.py","MySqlTools.py","2222.xls","1111.xls","测试csv.csv"]

        //2.相对路径
        File file1 = new File("../io/data.txt");
        System.out.println(file1.getName()); //data.txt
        //获取文件绝对路径（不会解析符号..和.）
        System.out.println(file1.getAbsolutePath());// /Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/../io/data.txt
        //获取文件绝对路径（会解析符号..和.）
        System.out.println(file1.getCanonicalPath());// /Users/yangjunwei/IdeaProjects/JavaAdvanced/io/data.txt

        //3.jvm启动目录
        File startFile = new File(".");
        //获取jvm启动目录的绝对路径
        System.out.println(startFile.getAbsolutePath());

        //4.启动目录
        File file2 = new File("src/main/java/com/albert/javase/io/");
        System.out.println(JsonUtil.toString(file2.list()));
        System.out.println(file2.getAbsolutePath());

    }

    /**
     * File类的实例化
     */
    @Test
    public void fileInit() {
        // File(String pathname)
        File file = new File("data.txt");
        File file1 = new File("/Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/src/main/java/com/albert/javase/io/data.txt");
        System.out.println(file);
        System.out.println(file1);
        // File(String parent, String child)
        File file2 = new File("/Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/", "src/main/java/com/albert/javase/io");
        System.out.println(file2);
        // File(File parent, String child)
        File file3 = new File(file2, "data.txt");
        System.out.println(file3);
    }

    /**
     * 常用方法练习
     */
    @SneakyThrows
    @Test
    public void methodTest() {
        File file = new File("src/main/java/com/albert/javase/io/data.txt");
        //获取名称
        System.out.println(file.getName());
        //获取路径
        System.out.println(file.getPath());
        //获取上层文件目录
        System.out.println(file.getParent());
        //获取文件长度
        System.out.println(file.length());
        //获取最后一次修改时间
        System.out.println(file.lastModified());

        //2.相对路径
        File file1 = new File("../io/data.txt");
        System.out.println(file1.getName()); //data.txt
        //获取文件绝对路径（不会解析符号..和.）
        System.out.println(file1.getAbsolutePath());// /Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/../io/data.txt
        //获取文件绝对路径（会解析符号..和.）
        System.out.println(file1.getCanonicalPath());// /Users/yangjunwei/IdeaProjects/JavaAdvanced/io/data.txt

        //适用于文件目录
        File file2 = new File("src/main/java/com/albert/javase/io/");
        //获取指定文件目录下所有文件或文件目录的名称数组
        String[] list = file2.list();
        System.out.println(JsonUtil.toString(list));
        //获取指定文件目录下所有文件或文件目录的file数组
        File[] files = file2.listFiles();
        System.out.println(JsonUtil.toString(files));
    }

    /**
     * 测试File类的重命名功能
     * renameTo
     */
    @Test
    public void renameFile(){
        //旧文件
        File file = new File("/Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/src/main/java/com/albert/javase/io/data.txt");
        //新文件（不能已存在）
        File newFile = new File("/Users/yangjunwei/IdeaProjects/JavaAdvanced/javase-practice/src/main/java/com/albert/javase/io/data.txt");
        boolean b = file.renameTo(newFile);
        System.out.println(b);
    }


}
