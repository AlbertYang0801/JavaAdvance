package com.albert.concurrentpractice.book.chapterthree;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * 线程安全的Map练习
 * @author Albert
 * @date 2021/1/18 下午6:22
 */
public class ThreadSafeMap_15 {

    public static void main(String[] args) {
        Map<String,String> oldMap = Maps.newHashMap();
        //使用Collections对map进行线程同步封装
        Map<String,String> safeMap = Collections.synchronizedMap(oldMap);

        //线程安全的Map
        ConcurrentMap<Object, Object> map = Maps.newConcurrentMap();
    }


}
