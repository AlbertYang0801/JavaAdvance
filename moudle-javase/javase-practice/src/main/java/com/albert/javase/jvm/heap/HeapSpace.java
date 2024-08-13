package com.albert.javase.jvm.heap;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yjw
 * @date 2022/4/3 15:23
 */
public class HeapSpace {

    @SneakyThrows
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a);
                //字符串翻倍增长，占满堆内存
                a = a + a;
                i++;
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);
        }
        Thread.sleep(10000);
    }


}
