package com.albert.redis.lock;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁2 - 解决超卖问题
 * 使用 Redissson
 *
 * @author yangjunwei
 * @date 2021/7/23 4:40 下午
 */
@Service
public class RedissonGoodService {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final String GOOD_KEY = "good:110110";

    private final String GOOD_LOCK = "lock:110110";

    public String testRedisson() {
        //分布式锁
        RLock rLock = redissonClient.getLock(GOOD_LOCK);
        //redisson加锁
        rLock.lock(1000L, TimeUnit.SECONDS);
        try {
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
        } finally {
            //加锁状态 当前线程
            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                //解锁
                rLock.unlock();
            }
        }
    }

    private void redissonDoc() throws InterruptedException {
        //1. 普通的可重入锁
        RLock lock = redissonClient.getLock("generalLock");
        // 拿锁失败时会不停的重试
        // 具有Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
        lock.lock();
        // 尝试拿锁10s后停止重试,返回false
        // 具有Watch Dog 自动延期机制 默认续30s
        boolean res1 = lock.tryLock(10, TimeUnit.SECONDS);
        // 拿锁失败时会不停的重试
        // 没有Watch Dog ，10s后自动释放
        lock.lock(10, TimeUnit.SECONDS);
        // 尝试拿锁100s后停止重试,返回false
        // 没有Watch Dog ，10s后自动释放
        boolean res2 = lock.tryLock(100, 10, TimeUnit.SECONDS);
        //2. 公平锁 保证 Redisson 客户端线程将以其请求的顺序获得锁
        RLock fairLock = redissonClient.getFairLock("fairLock");
        //3. 读写锁 没错与JDK中ReentrantLock的读写锁效果一样
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("readWriteLock");
        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();
    }


}
