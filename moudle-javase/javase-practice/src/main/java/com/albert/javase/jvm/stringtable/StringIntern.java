package com.albert.javase.jvm.stringtable;

/**
 * @author yjw
 * @date 2022/4/5 11:41
 */
public class StringIntern {

    //StringTable ["ab","a","b"]
    public static void main(String[] args) {
        //常量入串池
        String s = "ab";

        //"a","b" 入串池
        //new String("a") new String("b") 堆中构建新对象
        //new String("ab") 存在于堆中
        String s1 = new String("a") + new String("b");

        //intern()方法，1.8 ===》 堆中对象放入串池，若串池存在，返回串池的对象，若串池不存在，则放入堆中的对象。
        String intern = s1.intern();

        System.out.println(s == s1);
        System.out.println(s == intern);

    }

}
