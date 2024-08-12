package com.albert.spring.aop.bytebuddy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
public class PoliteInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //拦截@Polite注解
        if (method.getAnnotation(Polite.class) != null) {
            String ret = (String) method.invoke(proxy, args);
            if (ret.endsWith(".")) {
                ret = ret.substring(0, ret.length() - 1) + "!";
            }
            return ret;
        }
        //放行
        return method.invoke(proxy, args);
    }


}
