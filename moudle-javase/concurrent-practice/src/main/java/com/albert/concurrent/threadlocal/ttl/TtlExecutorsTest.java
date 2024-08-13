package com.albert.concurrent.threadlocal.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 修饰线程池
 *
 * @author yangjunwei
 * @date 2024/7/29
 */
@Slf4j
public class TtlExecutorsTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();

    public static void init() {
        //使用ttl修饰executorService
        executorService = TtlExecutors.getTtlExecutorService(executorService);
    }

    public void test() {
        ttl.set("parent-set-value");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                log.info("ttl value -> {}", ttl.get());
            }
        };
        executorService.submit(task);
    }

    public static void main(String[] args) {
        new TtlExecutorsTest().test();
    }


}
