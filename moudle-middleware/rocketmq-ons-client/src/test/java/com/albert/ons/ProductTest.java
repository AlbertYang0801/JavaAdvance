package com.albert.ons;

import com.albert.ons.producer.TestMessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangjunwei
 * @date 2025/5/21 11:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTest {

    @Autowired
    TestMessageProducer testMessageProducer;

    @Test
    public void testSend(){
        testMessageProducer.reportTestMsg("666555444",10000L);
    }

}
