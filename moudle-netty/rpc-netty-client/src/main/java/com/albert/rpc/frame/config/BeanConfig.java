//package com.albert.rpc.frame.config;
//
//import com.albert.rpc.frame.rpcclient.RemoteInterfaceProxy;
//import com.albert.rpc.remote.rpc.SendSms;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author yangjunwei
// * @date 2024-06-12
// */
//@Configuration
//public class BeanConfig {
//
//    @Autowired
//    private RemoteInterfaceProxy remoteInterfaceProxy;
//
//    @Bean
//    public SendSms getSmsService(){
//        return remoteInterfaceProxy.getRemoteProxyObject(SendSms.class);
//    }
//
//}
