package com.albert.middleware.annotations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import java.lang.annotation.*;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ConditionalOnExpression("'rocketmq'.equals('${global.message.middleware}')")
public @interface ConditionalGlobalMessageOnRocketMq {
}
