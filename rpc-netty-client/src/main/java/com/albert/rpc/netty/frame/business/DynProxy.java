package com.albert.rpc.netty.frame.business;

import org.springframework.stereotype.Service;

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

    private Class<?> serviceInterface;

    private ClientBusiHandler clientBusiHandler;

    public DynProxy(Class<?> serviceInterface, ClientBusiHandler clientBusiHandler) {
        this.serviceInterface = serviceInterface;
        this.clientBusiHandler = clientBusiHandler;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //组装 body
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("className", serviceInterface.getName());
        dataMap.put("methodName", method.getName());
        dataMap.put("paramType", method.getParameterTypes());
        dataMap.put("param", args);
        //调用 send
        return clientBusiHandler.send(dataMap);
    }


}
