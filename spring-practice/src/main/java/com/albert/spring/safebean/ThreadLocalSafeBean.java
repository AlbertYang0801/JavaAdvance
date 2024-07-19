package com.albert.spring.safebean;

import org.springframework.stereotype.Component;

/**
 * 第二种方法：使用ThreadLocal封装可变变量
 * @author Albert
 * @date 2021/3/12 上午12:05
 */
@Component
public class ThreadLocalSafeBean {

     /**
      * 使用ThreadLocal保证可变变量线程安全
      */
     ThreadLocal<String> threadLocalMsg = ThreadLocal.withInitial(()->"默认消息");

}
