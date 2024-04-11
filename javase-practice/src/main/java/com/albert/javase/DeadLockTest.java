package com.albert.javase;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockTest {


    private static ReentrantLock lockA = new ReentrantLock(false);

    private static ReentrantLock lockB = new ReentrantLock(false);


//    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println("第1个线程");
//            update();
//        }).start();
//        new Thread(() -> {
//            System.out.println("第2个线程");
//            add();
//        }).start();
//
//    }

    @SneakyThrows
    public static void add() {
        synchronized (lockA) {
            Thread.sleep(500);
            System.out.println("add");
            update();
            System.out.println(Thread.currentThread());
        }
    }

    @SneakyThrows
    public static void update() {
        synchronized (lockB) {
            Thread.sleep(500);
            System.out.println("update");
            System.out.println(Thread.currentThread());
            add();
        }
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        condition.await();
        condition.signal();
        condition.notify();
        condition.wait();
        ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();
        objectThreadLocal.get();
        objectThreadLocal.remove();
    }

    public static void main(String[] args) {
        DeadLockTest o = new DeadLockTest();
        System.out.println(o);

        HashMap<Object,String> map = new HashMap<>();
        map.put(o,"1");

        o = null;
        System.out.println(o);

        map.put(null,"2");

        System.out.println(JSON.toJSONString(map));

    }

}
