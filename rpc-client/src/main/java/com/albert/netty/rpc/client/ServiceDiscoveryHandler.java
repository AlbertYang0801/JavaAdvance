package com.albert.netty.rpc.client;

import cn.hutool.core.collection.CollUtil;
import com.albert.netty.rpc.bean.RegisterServiceVo;
import com.albert.netty.rpc.config.RegCenterConfig;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 服务发现
 *
 * @author yjw
 * @date 2024/5/29 22:25
 */
@Service
public class ServiceDiscoveryHandler {

    @Autowired
    RegCenterConfig regCenterConfig;

    private static Random random = new Random();

    @SneakyThrows
    public InetSocketAddress getServiceRealAddress(String serviceName) {
        List<InetSocketAddress> serviceList = getServiceList(serviceName);
        //服务发现，从服务多个实例中随机选择一个(轮询，权重)
        InetSocketAddress inetSocketAddress = serviceList.get(random.nextInt(serviceList.size()));
        System.out.println("服务[" + serviceName + "]本次选择了:" + inetSocketAddress);
        return inetSocketAddress;
    }

    /**
     * 从注册中心获取服务真实地址
     *
     * @param serviceName
     * @return
     */
    public List<InetSocketAddress> getServiceList(String serviceName) throws Exception {
        Socket socket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(regCenterConfig.getHost(), regCenterConfig.getPort()));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            //服务发现
            outputStream.writeBoolean(true);
            outputStream.writeUTF(serviceName);
            outputStream.flush();

            Set<RegisterServiceVo> registerServiceVos = (Set<RegisterServiceVo>) inputStream.readObject();
            //服务提供者真实地址
            List<InetSocketAddress> inetSocketAddressList = CollUtil.emptyIfNull(registerServiceVos).stream()
                    .map(registerServiceVo -> new InetSocketAddress(registerServiceVo.getHostIp(), registerServiceVo.getPort()))
                    .collect(Collectors.toList());

            System.out.println("获得服务[" + serviceName + "]提供者的地址列表[" + inetSocketAddressList + "]，准备调用.");
            return inetSocketAddressList;
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
