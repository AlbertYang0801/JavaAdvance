package com.albert.study.thread.callable;

import com.albert.study.TestApplication;
import com.albert.study.utils.jackson.po.UserPO;
import com.albert.study.utils.jackson.utils.JsonUtil;
import com.albert.study.utils.localdatetime.utils.LocalDateTimeUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * 测试java多线程两大接口之一：Callable
 *
 * @author Albert
 * @date 2020/8/12 16:38
 */
@Slf4j
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class CallableTest {

    /**
     * 测试Callable的返回值,使用匿名内部类
     */
    @SneakyThrows
    @Test
    public void testCallable() {
        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                int i = 1;
                return String.valueOf((++i));
            }
        };
        try {
            String call = stringCallable.call();
            log.info("简单的方法调用，练习Callable接口匿名内部类的创建方式，结果：{}", call);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用lambda表达式，Callable是一个函数式接口
     */
    @SneakyThrows
    @Test
    public void testCallableLambda() {
        Callable<UserPO> userPOCallable = () -> {
            return UserPO.builder()
                    .name("测试返回参数")
                    .time(LocalDateTimeUtils.formatNow("yyyyMMddHHmmss"))
                    .build();
        };
        UserPO callUserPo = userPOCallable.call();
        log.info("获取Callable接口返回的数据:{}", JsonUtil.toString(callUserPo));
    }

    /**
     * 测试Callable接口和线程池一起使用
     * Callable接口创建线程的方式
     */
    @SneakyThrows
    @Test
    public void testThreadPool() {
        Callable<UserPO> userPOCallable = () -> {
            return UserPO.builder()
                    .userId("110")
                    .name("测试lambda表达式")
                    .build();
        };
        //手动创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        //submit执行线程，获取返回结果
        Future<UserPO> submit = executorService.submit(userPOCallable);
        UserPO userPO = submit.get();
        log.info(JsonUtil.toString(userPO));
        //关闭线程池
        executorService.shutdown();
    }


}
