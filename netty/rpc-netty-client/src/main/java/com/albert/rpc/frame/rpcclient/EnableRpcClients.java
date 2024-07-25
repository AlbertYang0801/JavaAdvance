package com.albert.rpc.frame.rpcclient;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yangjunwei
 * @date 2024-06-12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RpcClientsRegistrar.class)
public @interface EnableRpcClients {



}
