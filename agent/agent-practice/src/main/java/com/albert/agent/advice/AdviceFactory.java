package com.albert.agent.advice;

import com.albert.agent.advice.annotation.AdvicePointCut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 扫描切面
 *
 * @author yangjunwei
 * @date 2024/7/30
 */
public class AdviceFactory {

    //class:method
    private static Map<String, String> ADVICE_POINTCUT_MAP = new HashMap<>();

    private final static Set<String> CLASS_SET = new HashSet<>();

    /**
     * 注册
     *
     * @param advicePointCut
     */
    public static void registerAdvicePointCut(AdvicePointCut advicePointCut, Class<IAdvice> advice) {
        for (String clazz : advicePointCut.matchClasses()) {
            CLASS_SET.add(clazz);
            for (String method : advicePointCut.matchMethods()) {
                //类加方法纬度，注册切面类
                ADVICE_POINTCUT_MAP.put(buildKey(clazz, method), advice.getName());
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
        return ADVICE_POINTCUT_MAP.containsKey(buildKey(className, methodName));
    }

    /**
     * 匹配类
     *
     * @param className
     * @return
     */
    public static boolean matchClass(String className) {
        return CLASS_SET.contains(className);
    }

    private static String buildKey(String className, String methodName) {
        return className + ":" + methodName;
    }

    public static String insertBeforeContent(String className, String methodName, int methodParamSize) {
        String adviceName = ADVICE_POINTCUT_MAP.get(buildKey(className, methodName));
        StringBuilder args = new StringBuilder();
        if (methodParamSize > 0) {
            args.append("Object[] args = new Object[" + methodParamSize + "]; ");
            for (int i = 0; i < methodParamSize; i++) {
                args.append("args[" + i + "] = $" + (i + 1) + "; ");
            }
        }

        // 生成调用 interceptorClassName#methodName 的字节码指令
        StringBuilder content = new StringBuilder();
        content.append("{ ");
        if (methodParamSize > 0) {
            content.append(args);
        }
        content.append(adviceName);
        content.append(".");
        content.append("before($0");
        if (methodParamSize > 0) {
            content.append(",args");
        }
        content.append(");}");

        return content.toString();
    }



}
