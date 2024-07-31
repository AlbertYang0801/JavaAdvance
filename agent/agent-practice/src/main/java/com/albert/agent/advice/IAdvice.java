package com.albert.agent.advice;

/**
 * 所以切面类需要实现该类
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
public abstract class IAdvice {

    /**
     * 方法执行之前-增强逻辑
     *
     * @param target
     * @param args
     */
    static void before(Object target, Object[] args){

    }

    /**
     * 方法执行之后执行逻辑
     *
     * @param target
     * @param args
     */
    static void after(Object target, Object[] args){

    }


}

