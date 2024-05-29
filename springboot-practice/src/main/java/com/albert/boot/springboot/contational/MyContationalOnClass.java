package com.albert.boot.springboot.contational;

/**
 * @author yangjunwei
 * @date 2024-04-24
 */

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 条件注解
 * 根据 MyOnClassContational 逻辑判断是否生效
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(MyOnClassContational.class)
public @interface MyContationalOnClass {

    String className() default "";


}
