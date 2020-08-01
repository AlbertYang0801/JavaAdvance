package com.albert.study.redis.operate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis操作练习
 * @author Albert
 * @date 2020/7/31 17:18
 */
@Component
@Slf4j
public class RedisOperate {

    /**
     * 操作redis模版类
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 字符串为主的操作redis模版类
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向redis中添加key
     */
    public void setKey(Object key, Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e){
            log.error("set key to redis exception,e ",e);
        }
    }

    /**
     * 根据指定key值获取对应的value
     * @return
     */
    public String getValue(Object key){
        try {
            return String.valueOf(redisTemplate.opsForValue().get(key));
        }catch (Exception e){
            log.error("get key to redis exception,e ",e);
            return null;
        }
    }





}
