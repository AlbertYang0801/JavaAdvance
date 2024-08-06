package com.albert.rpc.framework.protocol.dubbo;

import com.albert.rpc.framework.Invocation;
import com.albert.rpc.framework.Invoker;
import com.albert.rpc.framework.URL;

/**
 * 客户端服务调用
 * @author yangjunwei
 * @date 2024/7/25
 */
public class DubboInvoker implements Invoker {

    private URL url;

    public DubboInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(), url.getPort(), invocation);
    }


}
