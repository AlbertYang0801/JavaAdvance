package com.albert.javase.file;

import cn.hutool.json.JSONUtil;
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
        File file = new File("/Users/yangjunwei/IdeaProjects/JavaAdvance/file-demo/file/");
        System.out.println(file);///Users/yangjunwei/IdeaProjects/JavaAdvance/file-demo/file
        System.out.println(JSONUtil.toJsonStr(file.list()));//["666.xls","CheckTest.py","MySqlTools.py","2222.xls","1111.xls","测试csv.csv"]

        //2.相对路径
        File file1 = new File("../file/data.txt");
        System.out.println(file1.getName()); //data.txt
        //获取文件绝对路径（不会解析符号..和.）
        System.out.println(file1.getAbsolutePath());// /Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/../file/data.txt
        //获取文件绝对路径（会解析符号..和.）
        System.out.println(file1.getCanonicalPath());// /Users/yangjunwei/IdeaProjects/JavaAdvance/file/data.txt

        //3.jvm启动目录
        File startFile = new File(".");
        //获取jvm启动目录的绝对路径
        System.out.println(startFile.getAbsolutePath());

        //4.启动目录
        File file2 = new File("src/main/java/com/albert/javase/file/");
        System.out.println(JSONUtil.toJsonStr(file2.list()));
        System.out.println(file2.getAbsolutePath());

    }

    /**
     * File类的实例化
     */
    @Test
    public void fileInit() {
        // File(String pathname)
        File file = new File("data.txt");
        File file1 = new File("/Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/src/main/java/com/albert/javase/file/data.txt");
        System.out.println(file);
        System.out.println(file1);
        // File(String parent, String child)
        File file2 = new File("/Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/", "src/main/java/com/albert/javase/file");
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
        File file = new File("src/main/java/com/albert/javase/file/data.txt");
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
        File file1 = new File("../file/data.txt");
        System.out.println(file1.getName()); //data.txt
        //获取文件绝对路径（不会解析符号..和.）
        System.out.println(file1.getAbsolutePath());// /Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/../file/data.txt
        //获取文件绝对路径（会解析符号..和.）
        System.out.println(file1.getCanonicalPath());// /Users/yangjunwei/IdeaProjects/JavaAdvance/file/data.txt

        //适用于文件目录
        File file2 = new File("src/main/java/com/albert/javase/file/");
        //获取指定文件目录下所有文件或文件目录的名称数组
        String[] list = file2.list();
        System.out.println(JSONUtil.toJsonStr(list));
        //获取指定文件目录下所有文件或文件目录的file数组
        File[] files = file2.listFiles();
        System.out.println(JSONUtil.toJsonStr(files));
    }

    /**
     * 测试File类的重命名功能
     * renameTo
     */
    @Test
    public void renameFile() {
        //旧文件
        File file = new File("/Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/src/main/java/com/albert/javase/file/data.txt");
        //新文件（不能已存在）
        File newFile = new File("/Users/yangjunwei/IdeaProjects/JavaAdvance/javase-practice/src/main/java/com/albert/javase/file/data.txt");
        boolean b = file.renameTo(newFile);
        System.out.println(b);
    }

    /**
     * 判断文件属性
     */
    @Test
    public void testFile() {
        File file = new File("src/main/java/com/albert/javase/file/data.txt");
        //是否是文件夹
        System.out.println(file.isDirectory());
        //是否是文件
        System.out.println(file.isFile());
        //是否存在
        System.out.println(file.exists());
        //是否可读
        System.out.println(file.canRead());
        //是否可写
        System.out.println(file.canWrite());
        //是否隐藏
        System.out.println(file.isHidden());
    }

    /**
     * 测试文件的创建删除
     */
    @SneakyThrows
    @Test
    public void testFileCreate() {
        File file = new File("rc/main/java/com/albert/javase/file/hello.txt");
        if (!file.exists()) {
            //创建新文件
            file.createNewFile();
            System.out.println("创建成功！");
        } else {
            file.delete();
            System.out.println("删除文件成功");
        }
    }

    /**
     * 测试文件夹的创建删除
     */
    @Test
    public void testFolder() {
        File file = new File("src/main/java/com/albert/javase/file/file1/file2/file3");
        if (!file.exists()) {
            //创建多级文件夹，如果上级目录不存在，一起创建
            boolean mkdir = file.mkdirs();
            System.out.println("创建多个文件夹结果======》" + mkdir);
        } else {
            //要想删除成功，file3文件夹下不能有文件
            boolean delete = file.delete();
            System.out.println("删除文件结果======》" + delete);
        }

        File newFile = new File("src/main/java/com/albert/javase/file/file1/file2/file4");
        //创建单个文件夹
        boolean mkdir = newFile.mkdir();
        System.out.println("创建单个文件夹结果======》" + mkdir);
    }


    //--------------------------------------------------练习题--------------------------------------------------

    /**
     * 1.利用File构造器，new一个文件目录file
     * 编写方法，实现删除file中指定文件的操作
     */
    @SneakyThrows
    @Test
    public void one() {
        String path = "src/main/java/com/albert/javase/file/one/";
        boolean b = deleteFile(new File(path));
        System.out.println(b);
    }

    private boolean deleteFile(File deleteFile) {
        if (!deleteFile.exists()) {
            return true;
        }
        //文件直接删除
        if (deleteFile.isFile()) {
            return deleteFile.delete();
        }
        //删除文件夹
        if (deleteFile.isDirectory()) {
            //获取文件夹下的所有文件夹或文件
            File[] files = deleteFile.listFiles();
            //先删除文件夹下的内容
            for (File file : files) {
                if (file.isDirectory()) {
                    //若是文件夹递归删除
                    this.deleteFile(file);
                } else {
                    //文件直接删除
                    file.delete();
                }
            }
            //删除文件夹
            deleteFile.delete();
        }
        return !deleteFile.exists();
    }

    /**
     * 2.判断指定目录下是否有后缀名为.jpg的文件，如果有，就输出该文件名称
     */
    @Test
    public void two(){
        String path="";
        File file = new File(path);

        String[] list = file.list();
        for (String fileName : list) {
            if(fileName.endsWith(".jpg")){
                System.out.println(fileName);
            }
        }
    }

    /**
     * 3. 遍历指定目录所有文件名称，包括子文件目录中的文件
     * 拓展1：并计算指定目录占用空间的大小
     * 拓展2：删除指定文件目录及其下的所有文件
     */
    @Test
    @SneakyThrows
    public void testThree(){
        String path = "src/main/java/com/albert/javase/file/one/";
        File file = new File(path);
        long folderLength = getFolderLength(file);
        System.out.println("文件目录占用空间大小=========》"+folderLength);
        boolean b = deleteFile(file);
        System.out.println(b);
    }

    private long getFolderLength(File file) {
        long length = 0;
        if(file.isFile()){
            length+=file.length();
            return length;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File singleFile : files) {
                if(singleFile.isDirectory()){
                    //递归
                    length+=this.getFolderLength(singleFile);
                }else{
                    length+=singleFile.length();
                }
            }
        }
        return length;
    }


}
