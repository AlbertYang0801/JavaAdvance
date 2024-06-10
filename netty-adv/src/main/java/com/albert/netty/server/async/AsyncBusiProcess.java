package com.albert.netty.server.async;

import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 某些任务可以异步来处理
 *
 * @author yjw
 * @date 2024/6/10 21:19
 */
@Slf4j
public class AsyncBusiProcess {

    public static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(3000);

    private static ExecutorService executorService = new ThreadPoolExecutor(1,
            NettyRuntime.availableProcessors(),60, TimeUnit.SECONDS,taskQueue);

    public static void submitTask(Runnable task){
        executorService.execute(task);
    }


}
