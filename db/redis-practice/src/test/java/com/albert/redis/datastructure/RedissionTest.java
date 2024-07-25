package com.albert.redis.datastructure;

import com.albert.redis.TestApplication;
import com.albert.redis.entry.SellScore;
import com.albert.redis.entry.SellScoreVo;
import com.albert.utils.jackson.JsonUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * 数据结构实战 - 测试类
 *
 * @author yangjunwei
 * @date 2021/7/19 7:57 下午
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedissionTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void stringTest() {
        RBucket<Map<String, String>> bucket = redissonClient.getBucket("test");
        Map<String, String> cacheMap = bucket.get();
        if (Objects.isNull(cacheMap)) {
            cacheMap = new HashMap<>();
        }
        cacheMap.put("test", "123");
        bucket.set(cacheMap);
    }

    @Test
    public void testRMap() {
        Map<String, String> cacheMap = redissonClient.getMap("APM:MINIO:CACHE:123");
        cacheMap.put("test123213", "123");
    }

    @Test
    public void testGetRMap() {
        Map<String, String> cacheMap = redissonClient.getMap("APM:MINIO:CACHE:20240326/");
        cacheMap.put("test123213", "123");
        System.out.println(JSON.toJSONString(cacheMap));
    }

}
