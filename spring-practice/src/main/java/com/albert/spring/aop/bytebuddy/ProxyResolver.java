package com.albert.spring.aop.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
public class ProxyResolver {

    ByteBuddy byteBuddy = new ByteBuddy();

    /**
     * 通过字节码技术对原始Bean进行增强。
     *
     * @param bean    原始Bean
     * @param handler 代理拦截器
     * @param <T>
     * @return
     */
    public <T> T createProxy(T bean, InvocationHandler handler) {
        Class<?> targetClass = bean.getClass();
        //动态创建Proxy类
        Class<?> proxyClass = this.byteBuddy
                //子类用默认无参构造方法
                .subclass(targetClass, ConstructorStrategy.Default.DEFAULT_CONSTRUCTOR)
                //拦截所有public方法
                .method(ElementMatchers.isPublic())
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //需要代理的bean，调用InvocationHandler的代理逻辑
                        return handler.invoke(bean, method, args);
                    }
                }))
                //生成字节码
                .make()
                //加载字节码
                .load(targetClass.getClassLoader()).getLoaded();
        Object proxy;
        try {
            //根据无参构造生成目标类的子类
            proxy = proxyClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (T) proxy;
    }


}
