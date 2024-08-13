package com.albert.javase.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//    public static void main(String[] args) {
//        AndJoiner andJoiner = new AndJoiner();
//
//        System.out.println("-----------------测试&------------");
//        if (andJoiner.falseFunc() & andJoiner.trueFunc()) {
//            System.out.println("测试&&");
//        }
//
//        System.out.println("-----------------测试&&------------");
//        if (andJoiner.falseFunc() && andJoiner.trueFunc()) {
//            System.out.println("测试&");
//        }
//
//    }

    public static boolean isHostIp(String ip) {
        //是否是ip port类型
        Pattern p = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\:(\\d+)");
        Matcher m = p.matcher(ip);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean hostIp = isHostIp("10.10.103.179:3306%33060");
        System.out.println(hostIp);
        String nodeName="10.10.103.179:3306%33060";
        String ip = nodeName.split(":")[0];
        String port = nodeName.split(":")[1];
        System.out.println(ip+"  "+port);
    }


}

/**
 * & 左右两个方法都会执行
 * && 有中断效果左边方法若为false，则不往下执行
 */