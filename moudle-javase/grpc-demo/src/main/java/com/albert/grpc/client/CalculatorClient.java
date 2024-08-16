package com.albert.grpc.client;

import com.albert.grpc.proto.CalculatorGrpc;
import com.albert.grpc.proto.CalculatorProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

/**
 * grpc客户端
 *
 * @author yangjunwei
 * @date 2024/8/16
 */
public class CalculatorClient {

    /**
     * 开启一个grpc客户端
     *
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

        CalculatorGrpc.CalculatorBlockingStub calculatorBlockingStub = CalculatorGrpc.newBlockingStub(channel);
        CalculatorProto.AddRequest addRequest = CalculatorProto.AddRequest.newBuilder().setNumber1(1).setNumber2(2).build();

        CalculatorProto.AddResponse response = calculatorBlockingStub.add(addRequest);
        System.out.println("resp result:" + response.getResult());
        channel.shutdown();
    }


}
