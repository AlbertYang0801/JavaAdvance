package com.albert.junit.aop;

import java.lang.annotation.*;

/**
 * @author yjw
 * @date 2024/3/28 0:06
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLogAspect {

    String oper();

    String methodName();


}
