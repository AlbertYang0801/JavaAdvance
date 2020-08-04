package com.albert.study.redis;

import com.albert.study.TestApplication;
import com.albert.study.redis.operate.RedisOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Albert
 * @date 2020/7/31 17:15
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisOperateTest {

    @Autowired
    private RedisOperate redisOperate;

    @Test
    public void testSetKey(){
        redisOperate.setKey(1614,100);
    }

    @Test
    public void testGetKey(){
        String value = redisOperate.getValue(1614);
        log.info("根据key获取的值为：{}",value);
    }


}
