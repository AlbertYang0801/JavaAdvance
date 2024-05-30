package com.albert.netty.rpc.regcenter;

import cn.hutool.core.collection.CollUtil;
import com.albert.netty.rpc.bean.RegisterServiceVo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 注册中心 服务注册和服务发现
 *
 * @author yjw
 * @date 2024/5/29 20:13
 */
public class RegisterService {

    //注册服务名,支持多实例
    //注册加锁，查询不加
    private static final Map<String, Set<RegisterServiceVo>> SERVICE_HOLDER = new HashMap<>();

    /**
     * 注册服务
     * 多个服务同时进行注册
     *
     * @param serviceName
     * @param hostIp
     * @param port
     */
    private static synchronized void registerServiceHolder(String serviceName, String hostIp, int port) {
        Set<RegisterServiceVo> registerServiceVos = SERVICE_HOLDER.get(serviceName);
        if (CollUtil.isEmpty(registerServiceVos)) {
            registerServiceVos = new HashSet<>();
            SERVICE_HOLDER.put(serviceName, registerServiceVos);
        }
        //注册服务
        registerServiceVos.add(new RegisterServiceVo(hostIp, port));
        System.out.println("service register success===> " + serviceName + " hostIp:port ===> " + hostIp + ":" + port);
    }

    public static void registerService(String serviceName, String hostIp, int port) {
        registerServiceHolder(serviceName, hostIp, port);
    }

    public static Set<RegisterServiceVo> getService(String serviceName) {
        return SERVICE_HOLDER.getOrDefault(serviceName,new HashSet<>());
    }


}
