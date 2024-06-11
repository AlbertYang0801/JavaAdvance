package com.albert.rpc.netty.frame.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * 代理远程服务接口，实现远程服务调用
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@Component
public class RemoteInterfaceProxy {

    @Autowired
    ClientBusiHandler clientBusiHandler;

    /**
     * 对接口进行动态代理
     * 最终调用实现了 InvocationHandler 的 DynProxy 类
     * @param serviceInterface
     * @param <T>
     * @return
     */
    public <T> T getRemoteProxyObject(final Class<?> serviceInterface) {
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},
                new DynProxy(serviceInterface,clientBusiHandler));
    }


}
