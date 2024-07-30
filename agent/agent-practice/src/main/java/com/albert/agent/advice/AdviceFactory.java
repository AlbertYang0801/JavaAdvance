package com.albert.agent.advice;

import com.albert.agent.advice.annotation.AdvicePointCut;

import java.util.HashSet;
import java.util.Set;

/**
 * 扫描切面
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
public class AdviceFactory {

    //class:method
    private static Set<String> ADVICE_POINTCUT_SET = new HashSet<>();

    private final static Set<String> METHOD_SET = new HashSet<>();

    /**
     * 注册
     *
     * @param advicePointCut
     */
    public static void registerAdvicePointCut(AdvicePointCut advicePointCut) {
        for (String clazz : advicePointCut.matchClasses()) {
            METHOD_SET.add(clazz);
            for (String method : advicePointCut.matchMethods()) {
                //类加方法纬度，注册切面类
                ADVICE_POINTCUT_SET.add(buildKey(clazz, method));
            }
        }
    }

    /**
     * 匹配类加方法
     *
     * @param className
     * @param methodName
     * @return
     */
    public static boolean matchClassAndMethod(String className, String methodName) {
        return ADVICE_POINTCUT_SET.contains(buildKey(className, methodName));
    }

    /**
     * 匹配方法
     *
     * @param className
     * @return
     */
    public static boolean matchClass(String className) {
        return METHOD_SET.contains(className);
    }

    private static String buildKey(String className, String methodName) {
        return className + ":" + methodName;
    }

    public static String insertBeforeContent(String className, String methodName) {
        // 生成调用 interceptorClassName#methodName 的字节码指令
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append(className);
        sb.append(".").append(methodName);
        // 传递 this 和可变参数
        sb.append(methodName).append("(this, $$); ");
        sb.append("}");
        return sb.toString();
    }

}
