package com.albert.javase.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yangjunwei
 * @date 2024/9/25
 */
public class IaInvocationHandler implements InvocationHandler {

    private String methodName;

    private String value;

    public IaInvocationHandler(String methodName, String value) {
        this.methodName = methodName;
        this.value = value;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(methodName.equals(method.getName())) {
            return value;
        }
        return null;
    }


}
