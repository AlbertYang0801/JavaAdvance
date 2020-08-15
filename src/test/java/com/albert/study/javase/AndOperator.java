package com.albert.study.javase;

import lombok.extern.slf4j.Slf4j;

/**
 * 与逻辑运算符的练习
 * Java中&&和&的区别
 *
 * @author Albert
 * @date 2020/8/15 23:58
 */
@Slf4j
public class AndOperator {


    public static void main(String[] args) {
        int i = 10;
        //测试&&的短路功能
        if (i < 10 && ++i > 0) {
            log.info("测试&&的短路功能，第一个表达式为false，&&不会执行第二个表达式,即&&不会执行++i,i={}", i);
        }

        if (i < 10 & ++i > 0) {
            log.info("测试&的全部执行，&两个表达式都会执行，即&会执行++i,i={}", i);
        }
        log.info("最终结果，&会执行第二个表达式++i,i=========> {}", i);
    }


}

/**
 * java中&&和&都是与运算符
 * 1.&&是如果第一个表达式返回false，不会执行第二个表达式
 * 2.&是两个表达式都会执行，即使第一个表达式返回false
 */