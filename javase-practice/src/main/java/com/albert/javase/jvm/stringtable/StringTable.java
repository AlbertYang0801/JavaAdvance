package com.albert.javase.jvm.stringtable;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yjw
 * @date 2022/4/4 19:56
 */
public class StringTable {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        //s1、s2是变量，所以需要重新new对象拼接，变为不可变对象，String对象是不可变对象。
        String s4 = s1 + s2;    // new StringBuilder.append("a").append("b").toString() ====> new String("ab")w
        System.out.println(s3 == s4);

        String s5="a"+"b";      //常量会在编译期做出优化，识别为常量（不会改变），去字符串常量池
        System.out.println(s3 == s5);

    }

}
