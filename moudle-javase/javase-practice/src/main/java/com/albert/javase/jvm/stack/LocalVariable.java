package com.albert.javase.jvm.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 局部变量是否安全
 * @author yjw
 * @date 2022/4/3 11:56
 */
public class LocalVariable implements Runnable{

    @Override
    public void run() {
         int x = 0;
        for (int i = 0; i < 5000; i++) {
            x++;
        }
        System.out.println(x);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;i++){
            executorService.submit(new LocalVariable());
        }
        executorService.shutdown();
    }


}
