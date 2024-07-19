package com.albert.spring.safebean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 第一种方法：修改Bean的作用域为prototype 来保证线程安全
 * @author Albert
 * @date 2021/3/12 上午12:05
 */
@Component
@Scope("prototype")
public class BeanScopeSafeBean {

     String msg = "默认消息";

}
