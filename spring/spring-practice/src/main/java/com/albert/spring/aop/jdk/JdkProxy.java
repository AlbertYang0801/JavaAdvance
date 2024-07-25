package com.albert.spring.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author yjw
 * @date 2024/3/28 1:08
 */
public class JdkProxy implements InvocationHandler {

    private UserService userService;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理
        System.out.println("start proxy");
        //这里是运用了反射机制的invoke方法，执行了userService这个目标对象中的方法
        method.invoke(userService,args);
        System.out.println("end proxy");
        return proxy;
    }

    public UserService createProxy(UserService userService){
        this.userService=userService;
        //获取类加载器
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        //被代理对象(也就是要增强处理的类)实现所有的接口
        Class<?>[] interfaces = userService.getClass().getInterfaces();
        //增强了，返回的是代理后的对象。
         return (UserService) Proxy.newProxyInstance(classLoader,interfaces,this);
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        JdkProxy jdkProxy = new JdkProxy();
        UserService proxy = jdkProxy.createProxy(userService);
        proxy.createUser();
    }


}
