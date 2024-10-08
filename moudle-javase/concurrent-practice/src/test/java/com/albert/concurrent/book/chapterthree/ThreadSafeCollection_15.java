package com.albert.concurrent.book.chapterthree;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全的集合练习
 *
 * @author Albert
 * @date 2021/1/18 下午6:22
 */
public class ThreadSafeCollection_15 {

    public static void main(String[] args) {

        //***********************包装map***************************
        Map<String, String> oldMap = Maps.newHashMap();
        //使用Collections对map进行线程同步封装
        Map<String, String> safeMap = Collections.synchronizedMap(oldMap);
        safeMap.put(null, "a");
        System.out.println(safeMap);
        //线程安全的Map
        Map<String,String> map = new Hashtable<>();
        map.put("1","1");


        //***********************包装list***************************
        ArrayList<Object> oldList = Lists.newArrayList();
        //使用Collections对list进行线程同步封装
        List<Object> safeList = Collections.synchronizedList(oldList);

        //***********************vector***************************
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        System.out.println(JSONUtil.toJsonStr(vector));


        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        System.out.println(JSONUtil.toJsonStr(copyOnWriteArrayList));

        //***********************注意***************************
        //线程安全的Map
        ConcurrentMap<String, String> concurrentMap = Maps.newConcurrentMap();
        //concurrentMap的key或者value==null都会报空指针
        concurrentMap.put("a", null);
        System.out.println(JSONUtil.toJsonStr(concurrentMap));

    }


}


/**
 * Collections封装的线程安全的集合，是因为其内部有个object对象，以该对象作为锁加在集合的相关方法上。
 * 在每个方法执行之前都要获得对象锁，效率较低。
 */
