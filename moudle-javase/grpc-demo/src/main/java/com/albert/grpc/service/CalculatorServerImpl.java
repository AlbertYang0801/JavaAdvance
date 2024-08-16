package com.albert.grpc.service;

import com.albert.grpc.proto.CalculatorGrpc;
import com.albert.grpc.proto.CalculatorProto;
import io.grpc.stub.StreamObserver;

/**
 * @author yangjunwei
 * @date 2024/8/16
 */
public class CalculatorServerImpl extends CalculatorGrpc.CalculatorImplBase {

    /**
     * 重写proto定义的方法
     * @param request
     * @param responseObserver
     */
    @Override
    public void add(CalculatorProto.AddRequest request, StreamObserver<CalculatorProto.AddResponse> responseObserver) {
        int result = request.getNumber1()+ request.getNumber2();
        CalculatorProto.AddResponse addResponse = CalculatorProto.AddResponse.newBuilder().setResult(result).build();
        responseObserver.onNext(addResponse);
        responseObserver.onCompleted();
    }



}
