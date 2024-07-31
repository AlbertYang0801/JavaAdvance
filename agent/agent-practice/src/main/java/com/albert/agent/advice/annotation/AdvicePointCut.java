package com.albert.agent.advice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangjunwei
 * @date 2024/7/30
 */
//在运行时无法通过反射获取注解
@Retention(RetentionPolicy.RUNTIME)
//用于类、接口、枚举
@Target(ElementType.TYPE)
public @interface AdvicePointCut {

    /**
     * 匹配的类
     * @return
     */
    String[] matchClasses();

    /**
     * 匹配的方法
     * @return
     */
    String[] matchMethods() default {};

    /**
     * 匹配的参数
     * @return
     */
    String[] matchParams() default {};


}
