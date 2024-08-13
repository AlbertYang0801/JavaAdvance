package com.albert.concurrent.threadlocal.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 修饰Runnable
 * @author yangjunwei
 * @date 2024/7/29
 */
@Slf4j
public class TtlRunnableTest {

    TransmittableThreadLocal<String> ttl = new TransmittableThreadLocal<>();

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @SneakyThrows
    public void fillRunnable() {
        ttl.set("value-set-in-parent");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String content = ttl.get();
                log.info("获取数据：{}", content);
            }
        };
        //使用ttl修饰过的Runnable，获取的ttl为当前值
        TtlRunnable ttlRunnable = TtlRunnable.get(task);
        executorService.submit(ttlRunnable);

        ttl.set("value-modified-in-parent");
        //主线程修改value，但是获取到的还是修饰之前的
        executorService.submit(ttlRunnable);

        Thread.sleep(100);
        //重新执行修饰
        executorService.submit(TtlRunnable.get(task));
    }

    public static void main(String[] args) {
        new TtlRunnableTest().fillRunnable();
    }


}
