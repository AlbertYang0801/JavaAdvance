package com.albert.rpc.framework.protocol.dubbo;

import com.albert.rpc.framework.Invoker;
import com.albert.rpc.framework.Protocol;
import com.albert.rpc.framework.URL;
import com.albert.rpc.framework.register.LocalRegister;
import com.albert.rpc.framework.register.RemoteMapRegister;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class DubboProtocol implements Protocol {

    @Override
    public void register(URL url) {
        //服务类注册
        LocalRegister.regist(url.getInterfaceName(), url.getImplClass());
        RemoteMapRegister.regist(url.getInterfaceName(), url);
        //开启netty服务
        new NettyServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Invoker refer(URL url) {
        return new DubboInvoker(url);
    }


}
