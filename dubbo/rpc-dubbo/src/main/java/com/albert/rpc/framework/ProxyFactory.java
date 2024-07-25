package com.albert.rpc.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class ProxyFactory<T> {

    /**
     * 获取接口代理类
     * 注册提供RPC服务的接口
     *
     * @param interfaceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String mock = System.getProperty("mock");
                        if (mock != null && mock.startsWith("return:")) {
                            return mock.replace("return:", "");
                        }
                        Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
                        Invoker invoker = ClusterInvoker.join(interfaceClass);
                        return invoker.invoke(invocation);
                    }
                });
    }


}
