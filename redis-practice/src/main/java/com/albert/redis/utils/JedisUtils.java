package com.albert.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;

/**
 * @author yangjunwei
 * @date 2021/7/23 10:06 上午
 */
public class JedisUtils {

    public static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool = new JedisPool(jedisPoolConfig,"localhost",6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }


}
