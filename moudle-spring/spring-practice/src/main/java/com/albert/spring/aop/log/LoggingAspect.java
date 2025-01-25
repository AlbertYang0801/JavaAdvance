package com.albert.spring.aop.log;

import com.albert.spring.aop.AuditLogAspect;
import com.albert.spring.entity.req.BaseRequest;

import cn.hutool.json.JSONUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangjunwei
 * @date 2025/1/16 18:27
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.albert.spring.aop.log.LogOperation)")
    public void logOperationPointcut() {
    }

    @Before("logOperationPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        LogOperation logOperation = getAnnotation(joinPoint);
        if (logOperation != null) {
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            logger.info("Entering method: {} with arguments: {}", methodName, args);
        }
    }

    /**
     * 拦截加了注解的方法
     * 1.记录入参出参
     * 2.记录接口耗时
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logOperationPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        LogOperation logOperation = getAnnotation(joinPoint);
        if (logOperation != null) {
            long startTime = System.currentTimeMillis(); // 记录开始时间

            String methodName = joinPoint.getSignature().getName();
            String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

            // 获取当前请求
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 获取URL
            String url = request.getRequestURL().toString();
            System.out.println("请求URL：" + url);

            String clientIpAddress = getClientIpAddress(request);
            System.out.println("客户端ip："+clientIpAddress);

            // 入参
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof BaseRequest) {
                    BaseRequest baseRequest = (BaseRequest) arg;
                    logger.info("当前登录用户：{}", baseRequest.getUserId());
                }
            }

            logger.info("方法：{}", methodName);
            logger.info("注解内容：{}", logOperation.value());
            logger.info("入参：{}", JSONUtil.toJsonStr(args));

            Object result = joinPoint.proceed(); // 执行目标方法

            logger.info("出参：{}", result);

            long endTime = System.currentTimeMillis(); // 记录结束时间
            long executionTime = endTime - startTime; // 计算耗时
            logger.info("方法执行耗时：{} ms", executionTime);

            return result; // 返回目标方法的返回值
        } else {
            return joinPoint.proceed(); // 如果没有注解，直接执行目标方法
        }
    }

    // @AfterReturning(pointcut = "logOperationPointcut()", returning = "result")
    // public void logAfterReturning(JoinPoint joinPoint, Object result) {
    //     LogOperation logOperation = getAnnotation(joinPoint);
    //     if (logOperation != null) {
    //         String methodName = joinPoint.getSignature().getName();
    //         //入参
    //         Object[] args = joinPoint.getArgs();
    //         for (Object arg : args) {
    //             if(arg instanceof BaseRequest){
    //                 BaseRequest baseRequest = (BaseRequest) arg;
    //                 logger.info("当前登录用户：{}",baseRequest.getUserId());
    //             }
    //         }
    //
    //         logger.info("方法：{}", methodName);
    //         logger.info("注解内容：{}", logOperation.value());
    //         logger.info("入参：{}", JSONUtil.toJsonStr(args));
    //         logger.info("出参：{}", result);
    //     }
    // }

    private LogOperation getAnnotation(JoinPoint joinPoint) {
        return ((org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature()).getMethod()
            .getAnnotation(LogOperation.class);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



}
