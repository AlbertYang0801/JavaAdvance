package com.albert.agent.advice.impl;

import com.albert.agent.advice.annotation.AdvicePointCut;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

/**
 * 异常切面拦截
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
//TODO java.lang.Throwable jre里面类不能被增强
@AdvicePointCut(matchClasses = {"java.lang.Throwable"}, matchMethods = {"getMessage", "printStackTrace"})
@Slf4j
public class ExceptionMessageAdvice {

    public void before(JoinPoint joinPoint) {
        System.out.println("拦截到异常信息");
        Throwable t = (Throwable) joinPoint;
        log.error(t.getClass().getName());
        log.error(t.getMessage());
    }


}
