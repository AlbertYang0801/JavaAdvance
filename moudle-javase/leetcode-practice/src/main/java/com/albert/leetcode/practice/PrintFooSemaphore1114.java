package com.albert.leetcode.practice;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * leetcode 1114. 按序打印
 * 使三个线程按照顺序调用
 * 解题方法：使用Semaphore实现
 * 还可以使用阻塞队列进行实现(待完成)
 *
 * @author Albert
 * @date 2021/1/12 17:43
 */
@Slf4j
public class PrintFooSemaphore1114 {

    private Semaphore one = new Semaphore(0);

    private Semaphore two = new Semaphore(0);

    public PrintFooSemaphore1114() {

    }

    public void one() {
        log.info("one");
        System.out.println("one");
    }

    public void two() {
        log.info("two");
        System.out.println("two");
    }

    public void three() {
        log.info("three");
        System.out.println("three");
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        one.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        one.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        two.release();
        one.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        // printThird.run() outputs "third". Do not change or remove this line.
        two.acquire();
        printThird.run();
        two.release();
    }


}
