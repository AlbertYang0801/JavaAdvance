package com.albert.rpc.framework.protocol.http;

import com.albert.rpc.framework.Invoker;
import com.albert.rpc.framework.Protocol;
import com.albert.rpc.framework.URL;
import com.albert.rpc.framework.register.LocalRegister;
import com.albert.rpc.framework.register.RemoteMapRegister;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class HttpProtocol implements Protocol {

    @Override
    public void register(URL url) {
        //服务注册
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        //启动tomcat
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new HttpInvoker(url);
    }


}
