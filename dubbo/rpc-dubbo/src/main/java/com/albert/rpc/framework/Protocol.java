package com.albert.rpc.framework;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public interface Protocol {

    /**
     * 注册服务
     * 开启自身服务
     * @param url
     */
    void register(URL url);

    /**
     * 调用服务
     * @param url
     * @return
     */
    Invoker refer(URL url);

}
