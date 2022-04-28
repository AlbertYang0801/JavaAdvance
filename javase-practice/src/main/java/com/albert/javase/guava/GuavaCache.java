package com.albert.javase.guava;

import com.google.common.cache.*;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2022/4/12 3:23 下午
 */
public class GuavaCache {

    @Test
    @SneakyThrows
    public void testCache() {
        String key = "test";
        Cache<Object, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(100)//缓存最大容量
                .expireAfterWrite(5, TimeUnit.SECONDS)//过期时间
                .build();
        cache.put(key, "1");
        System.out.println(cache.getIfPresent(key));

        Thread.sleep(5000);
        System.out.println(cache.getIfPresent("1"));
    }

    @Test
    @SneakyThrows
    public void testCacheBuild() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                //并发级别为8，同时写缓存的线程数量最大为8
                .concurrencyLevel(8)
                //设置缓存的初始容量为10
                .initialCapacity(10)
                //缓存最大记录数为100个
                .maximumSize(100)
                //设置写缓存60s后过期
                .expireAfterWrite(10, TimeUnit.SECONDS)
                //设置缓存10s没有被访问，就过期
                .expireAfterAccess(5, TimeUnit.SECONDS)
                //设置统计缓存状态信息（命中率），通过cache.stats()方法查看
                .recordStats()
                .build();
        String k1 = "test1";
        String k2 = "test2";

        cache.put(k1, "123");
        cache.put(k2, "456");
        System.out.println(cache.getIfPresent(k1));
        System.out.println(cache.getIfPresent(k2));

        Thread.sleep(4000);
        System.out.println(cache.getIfPresent(k1));

        Thread.sleep(4000);
        System.out.println("缓存已过期：" + cache.getIfPresent(k2));

        Thread.sleep(4000);
        System.out.println(cache.getIfPresent(k1));
        System.out.println(cache.getIfPresent(k2));

    }

    @Test
    @SneakyThrows
    public void testCacheSize() {
        Cache<Object, Object> cache = CacheBuilder.newBuilder()
                //限制最大容量
                .maximumWeight(1024 * 1024 * 1024)
                //最大容量计算
                .weigher((k, v) -> k.toString().getBytes().length + v.toString().getBytes().length)
                .build();
        cache.put("a", "a");
        Object a = cache.getIfPresent("a");
        System.out.println(a);
    }

    @Test
    @SneakyThrows
    public void testLoadCache() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                //统计缓存的命中率
                .recordStats()
                .initialCapacity(10)
                //build中指定CacheLoader，可以在缓存不存在时通过该实现加载缓存
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return "www:" + s + ".com";
                    }
                });
        for (int i = 0; i < 10; i++) {
            //缓存不存在，通过CacheLoader自动加载
            String url = cache.get("baidu");
            System.out.println(url);
        }
        //打印缓存的命中情况
        System.out.println(cache.stats().toString());

        cache.cleanUp();
        //刷新缓存
        cache.refresh("xxx");
        System.out.println(cache.get("xxx"));

    }


}

