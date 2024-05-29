package com.albert.boot.springboot.contational;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author yangjunwei
 * @date 2024-04-24
 */
public class MyOnClassContational implements Condition {

    /**
     * 判断项目集成的服务器，是 tomcat 还是 jetty
     * 尝试 load class,若 load 成功，则意为使用对应的服务器
     *
     * @param conditionContext
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //注解内容
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(MyContationalOnClass.class.getName());
        //配置的 class 路径
        String className = (String) annotationAttributes.get("className");

        try {
            //尝试加载 class
            conditionContext.getClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}
