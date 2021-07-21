package com.albert.redis.lock;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

/**
 * 分布式锁 - 解决超卖问题
 *
 * @author yangjunwei
 * @date 2021/7/19 5:32 下午
 */
@Service
public class OversoldGoodService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final String GOOD_KEY = "good:110110";

    private final String GOOD_LOCK = "lock:110110";


    /**
     * 1.0 版本
     * 存在线程安全问题
     */
    public void oversoldGoodVersionOne() {
        //获取商品库存（不具备原子性）
        String stock = stringRedisTemplate.opsForValue().get(GOOD_KEY);
        Integer goods = StringUtils.isEmpty(stock) ? 0 : Integer.parseInt(stock);

        //有库存（线程不安全）
        if (goods > 0) {
            int realGoodCount = goods - 1;
            //减库存
            stringRedisTemplate.opsForValue().set(GOOD_KEY, String.valueOf(realGoodCount));
            System.out.println("成功买到商品");
            return;
        }
        System.out.println("商品已经卖完");
    }

    /**
     * 2.0 版本
     * 单机版 - 线程安全
     * 分布式线程不安全
     */
    public void oversoldGoodVersionTwo() {
        //加锁，解决单机版线程安全问题
        synchronized (GOOD_KEY) {
            //获取商品库存（不具备原子性）
            String stock = stringRedisTemplate.opsForValue().get(GOOD_KEY);
            Integer goods = StringUtils.isEmpty(stock) ? 0 : Integer.parseInt(stock);

            //有库存（线程不安全）
            if (goods > 0) {
                int realGoodCount = goods - 1;
                //减库存
                stringRedisTemplate.opsForValue().set(GOOD_KEY, String.valueOf(realGoodCount));
                System.out.println("成功买到商品");
                return;
            }
            System.out.println("商品已经卖完");
        }
    }

    /**
     * 3.0 版本
     * 加分布式锁 - 解决超卖问题
     */
    public String oversoldGoodVersionThree() {
        //加锁，解决单机版线程安全问题
        synchronized (GOOD_KEY) {
            //不断请求锁
            while (true) {
                //加分布式锁 setNx
                Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(GOOD_LOCK, UUID.randomUUID().toString());
                if (Objects.isNull(lock)) {
                    continue;
                }
                //获取到锁
                if (lock) {
                    //获取商品数量
                    String stock = stringRedisTemplate.opsForValue().get(GOOD_KEY);
                    int goods = StringUtils.isEmpty(stock) ? 0 : Integer.parseInt(stock);

                    if (goods <= 0) {
                        System.out.println("商品已经卖完了");
                        return "商品已经卖完";
                    }

                    //减库存
                    int realGoodCount = goods - 1;
                    stringRedisTemplate.opsForValue().set(GOOD_KEY, String.valueOf(realGoodCount));
                    System.out.println("成功买到商品");
                    //解锁
                    stringRedisTemplate.delete(GOOD_LOCK);
                    return "成功买到商品";
                }
            }
        }
    }

    

}
