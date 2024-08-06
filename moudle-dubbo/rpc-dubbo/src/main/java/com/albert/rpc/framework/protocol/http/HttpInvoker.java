package com.albert.rpc.framework.protocol.http;

import com.albert.rpc.framework.Invocation;
import com.albert.rpc.framework.Invoker;
import com.albert.rpc.framework.URL;


/**
 * 客户端-调用某个接口
 * @author yangjunwei
 * @date 2024/7/25
 */
public class HttpInvoker implements Invoker {

    private URL url;

    HttpInvoker(URL url) {
        this.url = url;
    }

    @Override
    public String invoke(Invocation invocation) {
        HttpClient httpClient = new HttpClient();
        //http调用接口
        return httpClient.send(url.getHostname(), url.getPort(), invocation);
    }


}
