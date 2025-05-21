package com.albert.ons.rocketmq;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMQMessageListener {

    /** topic name */
    String topic();

    /** tags */
    String tags() default "*";

    /** whether clustering consume */
    boolean isCluster() default false;

}
