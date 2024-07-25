package com.albert.rpc.provider;

import com.albert.rpc.framework.Protocol;
import com.albert.rpc.framework.ProtocolFactory;
import com.albert.rpc.framework.URL;
import com.albert.rpc.provider.api.HelloService;
import com.albert.rpc.provider.impl.HelloServiceImpl;

public class Provider {

    private static boolean isRun = true;

    public static void main(String[] args) {
        scanServiceProvider();
    }

    /**
     * 模拟扫描本地服务并注册
     */
    public static void scanServiceProvider() {
        //String protocolName = System.getProperty("protocol");
        String protocolName = "dubbo";
        URL url = new URL(protocolName, "localhost", 8999, HelloService.class.getName(), HelloServiceImpl.class);
        Protocol protocol = ProtocolFactory.getProtocol(protocolName);
        //注册url
        protocol.register(url);
    }


}
