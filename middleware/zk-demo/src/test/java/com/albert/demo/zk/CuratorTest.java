package com.albert.demo.zk;

import com.albert.demo.service.CuratorService;
import com.albert.demo.TestApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangjunwei
 * @date 2024-04-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Slf4j
public class CuratorTest {

    @Autowired
    CuratorService curatorService;

    @SneakyThrows
    @Test
    public void testLock(){
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                curatorService.create(finalI);
            }).start();
        }
        Thread.sleep(60000);
    }


}
