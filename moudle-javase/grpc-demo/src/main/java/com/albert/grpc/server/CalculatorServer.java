package com.albert.grpc.server;

import com.albert.grpc.service.CalculatorServerImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * grpc服务端
 * @author yangjunwei
 * @date 2024/8/16
 */
public class CalculatorServer {

    /**
     * 开启一个grpc服务端
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                //注册提供服务的类
                .addService(new CalculatorServerImpl())
                .build();
        server.start();
        System.out.println("server started");
        server.awaitTermination();
    }




}
