package com.albert.rpc.frame.rpcclient;

import ch.qos.logback.core.net.server.Client;
import cn.hutool.extra.spring.SpringUtil;
import com.albert.rpc.frame.netty.ClientBusiHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理逻辑类
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
public class DynProxy implements InvocationHandler {

    private final String annotationValue;

    public DynProxy(String annotationValue) {
        this.annotationValue = annotationValue;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装 body
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("className", annotationValue);
        dataMap.put("methodName", method.getName());
        dataMap.put("paramType", method.getParameterTypes());
        dataMap.put("param", args);
        //调用远程服务
        //懒加载模式
        return SpringUtil.getBean(ClientBusiHandler.class).send(dataMap);
    }


}
