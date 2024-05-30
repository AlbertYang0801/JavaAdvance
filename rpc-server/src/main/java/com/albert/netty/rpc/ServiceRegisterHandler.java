package com.albert.netty.rpc;

import com.albert.netty.rpc.config.RegCenterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务注册
 *
 * @author yjw
 * @date 2024/5/29 21:25
 */
@Service
public class ServiceRegisterHandler {

    @Autowired
    RegCenterConfig regCenterConfig;

    /**
     * 本地提供服务的名单，采用缓存实现
     */
    private static final Map<String, Class> SERVICE_CACHE = new ConcurrentHashMap<>();


    /**
     * 向注册中心注册本服务
     *
     * @param serviceName
     * @param hostIp
     * @param port
     * @param implClass
     */
    public void regRemote(String serviceName, String hostIp, int port, Class implClass) throws IOException {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(regCenterConfig.getHost(), regCenterConfig.getPort()));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            //注册服务
            outputStream.writeBoolean(false);
            outputStream.writeUTF(serviceName);
            outputStream.writeUTF(hostIp);
            outputStream.writeInt(port);
            outputStream.flush();

            boolean registerFlag = inputStream.readBoolean();
            if (registerFlag) {
                System.out.println("[Server]服务[" + serviceName + "]注册成功!");
            } else {
                System.out.println("[Server]服务[" + serviceName + "]注册失败!");
            }
            //服务放入本地缓存
            SERVICE_CACHE.put(serviceName, implClass);

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

    /**
     * 获取本地服务
     *
     * @param serviceName
     * @return
     */
    public Class getLocalService(String serviceName) {
        return SERVICE_CACHE.get(serviceName);
    }


}
