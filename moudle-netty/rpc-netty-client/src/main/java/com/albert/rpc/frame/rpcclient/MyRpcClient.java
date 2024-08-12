package com.albert.rpc.frame.rpcclient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@Retention(RetentionPolicy.RUNTIME) // 使注解在运行时可见
@Target(ElementType.TYPE) // 表示该注解可以应用于类上
public @interface MyRpcClient {

    /**
     * 服务名
     */
    String value() default "";

}
