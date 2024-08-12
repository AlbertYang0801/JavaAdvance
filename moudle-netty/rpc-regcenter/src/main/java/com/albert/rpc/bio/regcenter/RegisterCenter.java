package com.albert.rpc.bio.regcenter;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.rpc.bio.bean.RegisterServiceVo;
import com.albert.rpc.bio.config.RegCenterConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * 注册中心 服务端
 *
 * @author yjw
 * @date 2024/5/29 20:28
 */
@Service
@Order(1)
public class RegisterCenter {

    @Autowired
    RegCenterConfig regCenterConfig;

    private final static ExecutorService EXECUTOR =
            ThreadUtil.newExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 1024);

    private int regServerPort;

    /**
     * 注册中心的请求只有两种
     * 1.注册服务
     * 2.查询服务
     * 处理socket请求
     *
     * @param socket
     */
    public void handlerServerTask(Socket socket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            boolean isGetService = inputStream.readBoolean();
            System.out.println("receiver request getServer? " + isGetService);
            //查询服务
            if (isGetService) {
                String serviceName = inputStream.readUTF();
                Set<RegisterServiceVo> registerServiceVos = RegisterService.getService(serviceName);
                //返回服务给客户端
                outputStream.writeObject(registerServiceVos);
                outputStream.flush();
                System.out.println("将已经注册的服务[" + serviceName + "]提供给客户端");
            } else {
                //服务注册
                String serviceName = inputStream.readUTF();
                String host = inputStream.readUTF();
                int port = inputStream.readInt();
                //注册中心保存
                RegisterService.registerService(serviceName, host, port);
                outputStream.writeBoolean(true);
                outputStream.flush();
                System.out.println("将服务[" + serviceName + "]注册到服务端");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public void startService() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1", regServerPort));
            System.out.println("regCenter start port ===> " + regServerPort);
            while (true) {
                //监听请求注册中心的Socket
                Socket accept = serverSocket.accept();
                EXECUTOR.execute(() -> handlerServerTask(accept));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }

    @PostConstruct
    public void init() {
        this.regServerPort = regCenterConfig.getPort();
        //模拟注册中心服务端
        EXECUTOR.execute(this::startService);
    }


}

