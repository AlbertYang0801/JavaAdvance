package com.albert.spring.aop.bytebuddy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
public class PromClientProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType().equals(String.class) && parameters[i].getName().equals("query")) {
                args[i] = "被篡改";
                return method.invoke(proxy,args);
            }
        }
        //放行
        return method.invoke(proxy, args);
    }


}
