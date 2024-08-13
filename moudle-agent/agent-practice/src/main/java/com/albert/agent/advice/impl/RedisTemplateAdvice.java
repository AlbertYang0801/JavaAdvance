package com.albert.agent.advice.impl;

import com.albert.agent.advice.IAdvice;
import com.albert.agent.advice.annotation.AdvicePointCut;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangjunwei
 * @date 2024/7/31
 */
@AdvicePointCut(matchClasses = {"org.springframework.data.redis.core.DefaultValueOperations"}, matchMethods = {"set"})
@Slf4j
public class RedisTemplateAdvice extends IAdvice {

    public static void before(Object target, Object[] args) {
        for (Object arg : args) {
            log.info("arg = {}", arg);
        }
        if (args.length == 2) {
            log.info("agent 拦截到 ====> redis key:{} value:{}", args[0], args[1]);
        }
    }

    public static void after(Object target, Object[] args) {

    }


}
