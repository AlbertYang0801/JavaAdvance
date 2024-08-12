package com.albert.spring.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面
 * @author yjw
 * @date 2024/3/28 0:07
 */
@Aspect
@Slf4j
@Component
public class LogAspectj {

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.albert.spring.aop.AuditLogAspect)")
    public void logAspectPointcut() {
    }

    /**
     * Service层切点 注解拦截
     */
    @Pointcut("execution(* com.albert.spring.aop.service.*.*(..))")
    public void servicePointcut() {
    }

    /**
     * 单个方法拦截
     */
    @After(value = "execution(* com.albert.spring.aop.service.SystemService.addSystem(..))")
    public void after(){
        System.out.println("after addSystem........");
    }

    /**
     * 通知-在方法執行前
     * 对应的切点
     * @param joinPoint
     */
    @Before("logAspectPointcut()")
    public void controllerBefore(JoinPoint joinPoint){
        System.out.println("controller before");
    }

    /**
     * 环绕通知-指定切点
     * 解析注解
     * @param auditLogAspect
     */
    @Around(value = "logAspectPointcut() && @annotation(auditLogAspect)")
    public void controllerAround(AuditLogAspect auditLogAspect){
        //日志持久化
        log.info("执行了 {} 方法",auditLogAspect.methodName());
        log.info("执行了 {} 操作",auditLogAspect.oper());
    }

    @After("logAspectPointcut()")
    public void controllerAfter(JoinPoint joinPoint){
        System.out.println("controller after");
    }

    @Before("servicePointcut()")
    public void svcBefore(JoinPoint joinPoint){
        System.out.println("svc before");
    }

    @After("servicePointcut()")
    public void svcAfter(JoinPoint joinPoint){
        System.out.println("svc after");
    }

    @After("servicePointcut()")
    public void svcAfter2(JoinPoint joinPoint){
        System.out.println("svc after 2 ");
    }



}
