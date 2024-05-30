package com.albert.netty.rpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC客户端代理的部分
 * 传入某个类，对其进行增强
 * 使这个类可以进行远程服务调用，将远程服务结果返回
 *
 * @author yjw
 * @date 2024/5/29 22:16
 */
@Service
public class RpcClientFrame {

    @Autowired
    ServiceDiscoveryHandler serviceDiscoveryHandler;

    /**
     * 远程服务的代理对象，参数是客户端要调用的服务
     */
    public <T> T getRemoteProxyObject(final Class<?> serviceInterface) {
        //从注册中心获取服务真实地址
        InetSocketAddress serviceRealAddress = serviceDiscoveryHandler.getServiceRealAddress(serviceInterface.getName());
        //获取服务代理对象，由代理对象进行实际的网络调用
        //对Class执行InvocationHandler的Invoke方法，即JDK动态代理
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                new DynProxy(serviceInterface,
                        serviceRealAddress));
    }

    /**
     * JDK动态代理
     * 增强本地方法，实现对远程服务的访问
     */
    private static class DynProxy implements InvocationHandler {

        private Class<?> serviceInterface;
        /**
         * 远程服务地址
         */
        private InetSocketAddress address;

        public DynProxy(Class<?> serviceInterface, InetSocketAddress address) {
            this.serviceInterface = serviceInterface;
            this.address = address;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Socket socket = null;
            ObjectOutputStream outputStream = null;
            ObjectInputStream inputStream = null;
            try {
                socket = new Socket();
                socket.connect(address);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());

                //调用RPC服务端的方法
                outputStream.writeUTF(serviceInterface.getName());
                outputStream.writeUTF(method.getName());
                outputStream.writeObject(method.getParameterTypes());
                outputStream.writeObject(args);
                outputStream.flush();

                System.out.println(serviceInterface + " remote exec success!");
                return inputStream.readObject();
            } finally {
                if (socket != null) {
                    socket.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        }
    }

}
