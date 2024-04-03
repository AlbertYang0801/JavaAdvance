package com.albert.demo.zk;

import com.albert.demo.service.ZkLockService;
import com.albert.demo.TestApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ZkLockTest {

    @SneakyThrows
    @Test
    public void testLock() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                new ZkLockService().createOrder(finalI);
            }).start();
        }
        Thread.sleep(60000);
    }


}
