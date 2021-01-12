package com.albert.concurrentpractice.expand.lock.spinlock;

/**
 * 自旋锁的练习
 * 使用自旋锁实现可重入锁
 *
 * @author Albert
 * @date 2021/1/12 上午11:43
 */
public class ReentrantSpinLock extends SpinLock{

    private static int count = 0;

    @Override
    public void lock() {
        Thread thread = Thread.currentThread();
        if(atomicReference.get()==thread){
            count++;
            return;
        }
        while (!atomicReference.compareAndSet(null,thread)){
        }
    }

    @Override
    public void unlock(){
        Thread thread = Thread.currentThread();
        if(atomicReference.get()==thread){
            if(count==0){
                atomicReference.compareAndSet(thread,null);
            }
            count--;
        }
    }


}
