package com.albert.concurrentpractice.expand.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁的抽象类
 *
 * @author Albert
 */
public abstract class SpinLock {

    /**
     * 原子引用变量
     */
    public static AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public abstract void lock();

    public abstract void unlock();

}
