package com.albert.netty.rpc;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.netty.rpc.service.IStockService;
import com.albert.netty.rpc.service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * rpc框架的服务端
 *
 * @author yjw
 * @date 2024/5/29 21:34
 */
@Service
@Order(2)
public class RpcServerFrame {

    @Autowired
    private ServiceRegisterHandler serviceRegisterHandler;

    private final static ThreadPoolExecutor EXECUTOR = ThreadUtil.newExecutor(Runtime.getRuntime().availableProcessors(), 16);

    @PostConstruct
    public void server() throws Exception {
        Random r = new Random();
        int port = r.nextInt(100) + 9000;
        //RPC服务端开启服务
        this.startService(IStockService.class.getName(), "127.0.0.1", port, StockServiceImpl.class);
    }

    /**
     * 开启服务端
     * 向注册中心注册自己
     * 监听请求服务端的Socket
     *
     * @param serivceName
     * @param hostIp
     * @param port
     * @param implClass
     * @throws Exception
     */
    private void startService(String serivceName, String hostIp, int port, Class implClass) throws Exception {
        //开启服务端
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("[Server] RPC server on port:" + port);

        //向注册中心注册自己
        serviceRegisterHandler.regRemote(serivceName, hostIp, port, implClass);

        //监听服务端
        try {
            while (true) {
                //监听请求rpc服务端的Socket
                Socket accept = serverSocket.accept();
                EXECUTOR.execute(() -> serverTask(accept, serviceRegisterHandler));
            }
        } finally {
            serverSocket.close();
        }
    }

    /**
     * 处理服务请求任务
     * 根据客户端请求（类、方法名、入参类型、入参值）在本地执行方法获取结果，将结果返回给客户端
     *
     * @param socket
     * @param serviceRegisterHandler
     */
    public void serverTask(Socket socket, ServiceRegisterHandler serviceRegisterHandler) {
        System.out.println("[Server]receiver client request");
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            //远程调用的本质，调用远程方法就像调用本地方法一样
            //类、方法、方法入参类型、方法入参值
            //远程服务拿到这些信息在本地执行，将返回结果通过 Socket 连接返回
            String serviceName = inputStream.readUTF();
            String methodName = inputStream.readUTF();
            Class<?>[] parmTypes = (Class<?>[]) inputStream.readObject();
            Object[] args = (Object[]) inputStream.readObject();

            //本地找到Class对象
            Class implClass = serviceRegisterHandler.getLocalService(serviceName);
            if (implClass == null) {
                throw new ClassNotFoundException(serviceName + " Not Found");
            }

            //通过反射获取实际执行的方法
            Method method = implClass.getMethod(methodName, parmTypes);
            Object result = method.invoke(implClass.newInstance(), args);

            //将RPC服务端结果返回给客户端
            outputStream.writeObject(result);
            outputStream.flush();
            System.out.println("[Server]receiver client response success");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
