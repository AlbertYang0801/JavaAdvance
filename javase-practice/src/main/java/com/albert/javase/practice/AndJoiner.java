package com.albert.javase.practice;

/**
 * 练习&和&&的区别
 * @author Albert
 * @date 2020/12/21 下午5:51
 */
public class AndJoiner {

    private Boolean falseFunc() {
        System.out.println("执行了false方法");
        return false;
    }

    private Boolean trueFunc() {
        System.out.println("执行了true方法");
        return true;
    }

    public static void main(String[] args) {
        AndJoiner andJoiner = new AndJoiner();

        System.out.println("-----------------测试&------------");
        if (andJoiner.falseFunc() & andJoiner.trueFunc()) {
            System.out.println("测试&&");
        }

        System.out.println("-----------------测试&&------------");
        if (andJoiner.falseFunc() && andJoiner.trueFunc()) {
            System.out.println("测试&");
        }

    }


}

/**
 * & 左右两个方法都会执行
 * && 有中断效果左边方法若为false，则不往下执行
 */