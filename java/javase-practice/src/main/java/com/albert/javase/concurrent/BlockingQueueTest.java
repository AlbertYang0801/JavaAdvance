package com.albert.javase.concurrent;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author yangjunwei
 * @date 2024-06-30
 */
public class BlockingQueueTest {

    @SneakyThrows
    @Test
    public void test() {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        String poll = blockingQueue.poll(5, TimeUnit.SECONDS);
        System.out.println(poll);
    }

    @SneakyThrows
    @Test
    public void testSynchronizedQueue() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println("开始等待");
            synchronousQueue.offer("1");
            System.out.println("结束等待");
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = null;
            try {
                s = synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s);
        }).start();
        Thread.sleep(5000);
    }

    @SneakyThrows
    @Test
    public void testDelayQueue() {
        DelayQueue<OrderTask> delayQueue = new DelayQueue<>();

        new Thread(() -> {
            OrderTask taskA = new OrderTask("aaa", 5L,System.currentTimeMillis());
            delayQueue.put(taskA);
        }).start();
        new Thread(() -> {
            OrderTask taskB = new OrderTask("bbb", 3L,System.currentTimeMillis());
            delayQueue.put(taskB);
        }).start();

        OrderTask take = delayQueue.take();
        System.out.println(JSON.toJSONString(take));
        OrderTask take1 = delayQueue.take();
        System.out.println(JSON.toJSONString(take1));

        Thread.sleep(6000);
    }

    @SneakyThrows
    @Test
    public void testPriorityQueue() {
        PriorityBlockingQueue<OrderTask> queue = new PriorityBlockingQueue<>();

        new Thread(() -> {
            OrderTask taskA = new OrderTask("aaa", 5L,System.currentTimeMillis());
            queue.put(taskA);
        }).start();
        new Thread(() -> {
            OrderTask taskB = new OrderTask("bbb", 3L,System.currentTimeMillis());
            queue.put(taskB);
        }).start();

        Thread.sleep(1000);
        OrderTask take = queue.take();
        System.out.println(JSON.toJSONString(take));
        OrderTask take1 = queue.take();
        System.out.println(JSON.toJSONString(take1));

        Thread.sleep(6000);
    }

    @SneakyThrows
    @Test
    public void testLinkedBlockingQueue(){
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.take();
    }




}
