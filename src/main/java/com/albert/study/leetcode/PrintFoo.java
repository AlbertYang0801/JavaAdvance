package com.albert.study.leetcode;

import com.oracle.tools.packager.Log;

import java.util.concurrent.CountDownLatch;

/**
 * leetcode 1114. 按序打印
 * 使三个线程按照顺序调用
 * 解题方法：使用CountDownLatch()线程计数器
 * 还可以使用信号量和阻塞队列进行实现(待完成)
 * @author Albert
 * @date 2020/8/2 20:32
 */
public class PrintFoo {

    private CountDownLatch secondCountDownLatch = new CountDownLatch(1);

    private CountDownLatch thirdCountDownLatch = new CountDownLatch(1);

    public PrintFoo() {

    }

    public void one() {
        Log.info("one");
        System.out.println("one");
    }

    public void two() {
        Log.info("two");
        System.out.println("two");
    }

    public void three() {
        Log.info("three");
        System.out.println("three");
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        secondCountDownLatch.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        secondCountDownLatch.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        thirdCountDownLatch.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        thirdCountDownLatch.await();
        printThird.run();
    }


}
