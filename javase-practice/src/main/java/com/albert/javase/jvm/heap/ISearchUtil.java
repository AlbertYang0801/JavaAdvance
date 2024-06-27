package com.albert.javase.jvm.heap;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试jmap、jconsole、jvisualvm工具使用
 * @author yjw
 * @date 2022/4/3 16:20
 */
public class ISearchUtil {

    @Test
    @SneakyThrows
    public void jmap() {
        System.out.println("1111");
        Thread.sleep(20000);
        System.out.println("2222");
        //10MB
        byte[] bytes = new byte[1024 * 1024 * 10];
        Thread.sleep(10000);

        System.out.println("3333");
        System.gc();
        Thread.sleep(300000);
    }

    @Test
    @SneakyThrows
    public void jconsole() {
        System.out.println("1111");
        Thread.sleep(40000);
        System.out.println("2222");
        //10MB
        byte[] bytes = new byte[1024 * 1024 * 10];
        Thread.sleep(10000);
        System.out.println("3333");
        System.gc();
        Thread.sleep(300000);
    }

    @Test
    @SneakyThrows
    public void jvisualvm() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(new Student());
        }
        Thread.sleep(1000000L);
    }


}

class Student {
    byte[] big = new byte[1024 * 1024];
}
