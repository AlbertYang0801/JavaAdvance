package com.albert.concurrentpractice.book.chapterthree;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列的练习
 * 分析源码
 * @author Albert
 * @date 2021/1/19 上午11:13
 */
public class BlockQueue_17 {

    @SneakyThrows
    public static void main(String[] args) {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue(50);
        //入队
        blockingQueue.put("123");
        //入队，实际调用了offer()方法入队
        blockingQueue.add("234");
        //出队之后，队列不存在该元素
        Object one = blockingQueue.take();
        System.out.println(one);

        //队列元素进行遍历
        for (String str : blockingQueue) {
            System.out.println("循环："+str);
        }

    }


}

/**
 * add()方法，实际调用了offer()方法，增加了（Queue Full）的异常信息返回。
 * offer()方法，如果队列已满，无法存放，直接返回false。
 * put()方法，若队列已满，会进行线程等待，直到队列有空余位置，会将线程唤醒，进行插入操作。
 *
 * pool()方法，若队列为空，则返回null.
 * take()方法，若队列为空，会进行线程等待，直到队列不为空，会将等待线程唤醒，进行获取操作。
 *
 * 主要分析put()和take()方法，研究线程等待和线程唤醒的代码。
 */
