package com.albert.concurrent.lock.deadlock;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 活锁测试
 *
 * @author yangjunwei
 * @date 2021/10/27 3:21 下午
 */
@Slf4j
public class LivelockTest implements Runnable {

    private ReentrantLock oneLock;
    private ReentrantLock twoLock;

    public LivelockTest(ReentrantLock oneLock, ReentrantLock twoLock) {
        this.oneLock = oneLock;
        this.twoLock = twoLock;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            oneLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "======> lock -----"+oneLock.hashCode());
                Thread.sleep(100);
                //尝试加第二把锁（加不上直接释放第一把锁）
                if (twoLock.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "======> lock -----"+twoLock.hashCode());
                    } finally {
                        twoLock.unlock();
                        System.out.println(Thread.currentThread().getName() + "======> unlock！！！-----"+twoLock.hashCode());
                    }
                    return;
                }
            } finally {
                oneLock.unlock();
                System.out.println(Thread.currentThread().getName() + "======> unlock！！！-----"+oneLock.hashCode());
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock aLock = new ReentrantLock();
        ReentrantLock bLock = new ReentrantLock();
        LivelockTest threadA = new LivelockTest(aLock, bLock);
        LivelockTest threadB = new LivelockTest(bLock, aLock);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(threadA);
        executorService.submit(threadB);
        Thread.sleep(20000);
    }


}