package com.albert.study.test.countdownlatch;

import com.albert.study.TestApplication;
import com.albert.study.thread.countdownlatch.Boss;
import com.albert.study.thread.countdownlatch.Read;
import com.albert.study.thread.countdownlatch.Worker;
import com.albert.study.thread.countdownlatch.Write;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Albert
 * @date 2020/7/26 01:04
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class CountDownLatchTest {

    /**
     * 测试老板检查工人工作的例子
     */
    @Test
    public void testBossWatchWorker() {
        //创建缓存类型的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建一个为3的计时器
        CountDownLatch countDownLatch = new CountDownLatch(3);

        //创建Boss线程对象
        Boss boss = new Boss(countDownLatch);

        //创建3个工人线程对象
        Worker workerA = new Worker(countDownLatch, "孙圆圆");
        Worker workerB = new Worker(countDownLatch, "艾伯特");
        Worker workerC = new Worker(countDownLatch, "杨依惠");

        //线程添加到线程池中执行
        executorService.execute(workerA);
        executorService.execute(workerB);
        executorService.execute(workerC);
        executorService.execute(boss);

        try {
            //线程休眠，休眠结束关闭线程池
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //关闭线程池
        executorService.shutdown();

    }

    /**
     * 测试读和写操作
     * 读操作在写操作完成之后进行
     */
    @SneakyThrows
    @Test
    public void testRead(){
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建计数器
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Write write = new Write(countDownLatch);
        Read read = new Read(countDownLatch);

        executorService.execute(write);
        executorService.execute(read);

        //休眠5秒
        TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        //关闭线程池
        executorService.shutdown();

    }


}
