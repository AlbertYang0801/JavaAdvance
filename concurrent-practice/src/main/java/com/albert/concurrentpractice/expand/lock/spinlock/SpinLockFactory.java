package com.albert.concurrentpractice.expand.lock.spinlock;

/**
 * 工厂模式获取自旋锁
 *
 * @author Albert
 * @date 2021/1/12 上午11:55
 */
public class SpinLockFactory {

    /**
     * 获取可重入锁或不可重入锁
     *
     * @param reentry 是否可重入
     * @return
     */
    public static SpinLock getSpinLock(boolean reentry) {
        return reentry ? new ReentrantSpinLock() : new NoReentrantSpinLock();
    }


}
