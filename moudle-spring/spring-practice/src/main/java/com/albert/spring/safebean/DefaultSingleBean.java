package com.albert.spring.safebean;

import org.springframework.stereotype.Component;

/**
 * 默认的Bean在有可变变量的情况下存在线程安全问题
 * @author Albert
 * @date 2021/3/12 上午12:57
 */
@Component
public class DefaultSingleBean {

    String msg = "默认消息";

}
