package com.albert.netty.rpc.client;

import com.albert.netty.rpc.server.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author yjw
 * @date 2024/5/29 22:59
 */
@Order(3)
@Configuration
public class BeanConfig {

    @Autowired
    RpcClientFrame rpcClientFrame;

    /**
     * 注册远程服务接口
     * 创建远程服务接口的代理对象，实现调用远程服务的逻辑
     * @return
     */
    @Bean
    public IStockService iStockService(){
        return rpcClientFrame.getRemoteProxyObject(IStockService.class);
    }


}
