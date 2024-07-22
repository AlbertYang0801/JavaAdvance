package com.albert.spring.aop.bytebuddy;

import java.lang.annotation.*;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Polite {


}
