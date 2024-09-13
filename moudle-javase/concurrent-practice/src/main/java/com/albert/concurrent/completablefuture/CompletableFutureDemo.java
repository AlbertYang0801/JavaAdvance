package com.albert.concurrent.completablefuture;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author yangjunwei
 * @date 2024/9/9
 */
public class CompletableFutureDemo {

    @SneakyThrows
    public static String taskA() {
        Thread.sleep(2000);
        return "A";
    }

    @SneakyThrows
    public static String taskB() {
        Thread.sleep(5000);
        return "B";
    }

    @SneakyThrows
    public static void main(String[] args) {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(CompletableFutureDemo::taskA);
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(CompletableFutureDemo::taskB);
        CompletableFuture.allOf(futureA, futureB);

        //等待两个任务完成
        //try {
        //    CompletableFuture.allOf(futureA, futureB).get(1, TimeUnit.SECONDS);
        //}catch (TimeoutException e){
        //
        //}

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(futureA, futureB);
        //等待所有任务完成
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      voidCompletableFuture.get();
        System.out.println("allOf执行完成");

        //System.out.println(futureA.getNow("默认值A"));
        //System.out.println(futureB.getNow("默认值B"));
        System.out.println(futureA.get());
        System.out.println(futureB.get());
    }


}
