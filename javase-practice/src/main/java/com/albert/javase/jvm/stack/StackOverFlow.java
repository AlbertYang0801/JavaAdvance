package com.albert.javase.jvm.stack;

/**
 * 模拟虚拟方法栈溢出
 * @author yjw
 * @date 2022/4/3 11:22
 */
public class StackOverFlow {

    public void test(){
        System.out.println("111");
        say();
    }

    public void say(){
        System.out.println("say");
        test();
    }

    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        stackOverFlow.test();
    }

}
