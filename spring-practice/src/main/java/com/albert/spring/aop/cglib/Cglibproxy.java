package com.albert.spring.aop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yjw
 * @date 2024/3/28 1:54
 */
public class Cglibproxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("start proxy");
        methodProxy.invokeSuper(o, objects);
        System.out.println("end proxy");
        return o;
    }

    public Object createProxy(Object o) {
        Enhancer enhancer = new Enhancer();
        //确定要增强的类
        enhancer.setSuperclass(o.getClass());
        //指定代理类
        enhancer.setCallback(new Cglibproxy());
        return enhancer.create();
    }

    public static void main(String[] args) {
        //代理增强
        Cglibproxy cglibproxy =new Cglibproxy();
        //被代理对象
        UserService userService = new UserService();
        //代理类（被增强的类）
        UserService userServiceProxy = (UserService)cglibproxy.createProxy(userService);
        userServiceProxy.addSystem();
    }


}
