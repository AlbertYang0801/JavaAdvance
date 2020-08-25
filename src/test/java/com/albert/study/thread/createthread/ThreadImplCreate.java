package com.albert.study.thread.createthread;

import com.albert.study.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Albert
 * @date 2020/8/14 10:56
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadImplCreate {

    /**
     * 测试创建线程
     */
    @Test
    public void testImplRunnable() {
        RunnableImpl runnableImpl = new RunnableImpl();
        //普通的方法调用
        runnableImpl.run();

        //Runnable作为参数，传入Thread类，创建线程
        Thread thread = new Thread(runnableImpl);
        thread.start();

        log.info("这是主线程");
    }

    /**
     * 使用lambda表达式，传入的是Runnable接口
     */
    @Test
    public void testLambda(){
        new Thread(()->{
            log.info("lambad表达式创建的线程");
            System.out.println("增加线程内部逻辑");
        }).start();

        log.info("这是主线程，异步执行，比新线程执行快");
    }


    //Runnable函数式接口的子类对象
    class RunnableImpl implements Runnable {
        @Override
        public void run() {
            log.info("这是实现了Runnable的run()方法的新线程");
        }
    }


}
