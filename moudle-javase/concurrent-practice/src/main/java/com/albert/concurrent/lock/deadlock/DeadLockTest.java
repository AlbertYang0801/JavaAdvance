package com.albert.concurrent.lock.deadlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建死锁
 *
 * @author yangjunwei
 * @date 2021/10/27 11:09 上午
 */
@Slf4j
public class DeadLockTest implements Runnable {

    private ReentrantLock oneLock;
    private ReentrantLock twoLock;

    public DeadLockTest(ReentrantLock oneLock, ReentrantLock twoLock) {
        this.oneLock = oneLock;
        this.twoLock = twoLock;
    }

    @Override
    public void run() {
        oneLock.lock();
        try {
            log.info(Thread.currentThread().getName() + "======>第一把锁加锁");
            Thread.sleep(100);
            log.info(Thread.currentThread().getName() + "======>开始加第二把锁");
            twoLock.lock();
            try {
                log.info(Thread.currentThread().getName() + "======>第二把锁加锁");
                Thread.sleep(100);
            } finally {
                twoLock.unlock();
                log.info(Thread.currentThread().getName() + "======>第二把锁解锁完成！！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            oneLock.unlock();
            log.info(Thread.currentThread().getName() + "======>第一把锁解锁完成！！！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock aLock=new ReentrantLock();
        ReentrantLock bLock=new ReentrantLock();
        DeadLockTest threadA = new DeadLockTest(aLock,bLock);
        DeadLockTest threadB = new DeadLockTest(bLock,aLock);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(threadA);
        executorService.submit(threadB);
        Thread.sleep(20000);
    }


}
