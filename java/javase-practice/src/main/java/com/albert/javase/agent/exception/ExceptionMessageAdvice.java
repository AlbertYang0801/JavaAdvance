package com.albert.javase.agent.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 异常切面拦截
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
@Aspect
@Slf4j
public class ExceptionMessageAdvice {

    @Before(value = "execution(* java.lang.Throwable)")
    public void pointCut(){

    }

    public void before(JoinPoint joinPoint){
        System.out.println("拦截到异常信息");
        Throwable t = (Throwable)joinPoint;
        log.error(t.getClass().getName());
        log.error(t.getMessage());
    }


}
