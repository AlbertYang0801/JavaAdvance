package com.albert.rpc;

import com.albert.rpc.frame.rpcclient.EnableRpcClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Albert
 * @date 2020/9/7 17:15
 */
@EnableRpcClients
@SpringBootApplication
public class RpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }

}
