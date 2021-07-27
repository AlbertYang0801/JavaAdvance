package com.albert.redis.lock;

import com.albert.redis.utils.JedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁1 - 解决超卖问题
 * 使用 setnx 自己实现分布式锁
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

    /**
     * 4.0 版本
     * 加 finally 保证顺利解锁
     */
    public String oversoldGoodVersionFour() {
        try {
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
                    return "成功买到商品";
                }
            }
        } finally {
            //解锁
            stringRedisTemplate.delete(GOOD_LOCK);
        }
    }

    /**
     * 5.0 版本
     * 对分布式锁增加过期时间，保证解锁
     */
    public String oversoldGoodVersionFive() {
        try {
            //不断请求锁
            while (true) {
                //加分布式锁 setNx（增加过期时间）
                Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(GOOD_LOCK, UUID.randomUUID().toString(),
                        1000L, TimeUnit.SECONDS);
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
                    return "成功买到商品";
                }
            }
        } finally {
            //解锁
            stringRedisTemplate.delete(GOOD_LOCK);
        }
    }


    /**
     * 6.0 版本
     * 保证删除锁时删除的是自己线程创建的锁
     */
    public String oversoldGoodVersionSix() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().toString();
        try {
            //不断请求锁
            while (true) {
                //加分布式锁 setNx（增加过期时间）
                Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(GOOD_LOCK, value,
                        1000L, TimeUnit.SECONDS);
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
                    return "成功买到商品";
                }
            }
        } finally {
            //删除自己的锁(不具备原子性)
            if (Objects.requireNonNull(stringRedisTemplate.opsForValue().get(GOOD_LOCK)).equalsIgnoreCase(value)) {
                //解锁
                stringRedisTemplate.delete(GOOD_LOCK);
            }
        }
    }

    /**
     * 7.0 版本
     * finally 里面删除时，需要保证判断和删除操作的原子性
     * redis 事务
     */
    public String oversoldGoodVersionSeven() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().toString();
        try {
            //不断请求锁
            while (true) {
                //加分布式锁 setNx（增加过期时间）
                Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(GOOD_LOCK, value,
                        1000L, TimeUnit.SECONDS);
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
                    return "成功买到商品";
                }
            }
        } finally {
            //增加事务
            while (true) {
                //开启乐观锁监听
                stringRedisTemplate.watch(GOOD_LOCK);
                if (Objects.requireNonNull(stringRedisTemplate.opsForValue().get(GOOD_LOCK)).equalsIgnoreCase(value)) {
                    //开启事务
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(GOOD_LOCK);
                    //提交事务
                    List<Object> execList = stringRedisTemplate.exec();
                    if (execList.size() > 0) {
                        continue;
                    }
                }
                //取消监听
                stringRedisTemplate.unwatch();
                break;
            }
        }
    }

    /**
     * 8.0 版本
     * finally 里面删除时，需要保证判断和删除操作的原子性
     * lua 脚本
     */
    public String oversoldGoodVersionEight() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().toString();
        try {
            //不断请求锁
            while (true) {
                //加分布式锁 setNx（增加过期时间）
                Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(GOOD_LOCK, value,
                        1000L, TimeUnit.SECONDS);
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
                    return "成功买到商品";
                }
            }
        } finally {
            Jedis jedis = JedisUtils.getJedis();
            try {
                //lua脚本
                String script = "if redis.call('get',KEYS[1] == ARGV[1]) " +
                        "then " +
                        "return redis.call('del',KEYS[1] " +
                        "else " +
                        " return 0 " +
                        "end";
                Object eval = jedis.eval(script, Collections.singletonList(GOOD_LOCK), Collections.singletonList(value));
                if (Objects.equals("1", eval.toString())) {
                    System.out.println("使用 lua 脚本删除分布式锁成功！");
                } else {
                    System.out.println("使用 lua 脚本删除分布式锁失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (Objects.nonNull(jedis)) {
                    jedis.close();
                }
            }
        }
    }


}