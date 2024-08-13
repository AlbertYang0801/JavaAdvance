package com.albert.javase.collection;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangjunwei
 * @date 2021/9/28 8:47 下午
 */
public class ConcurrentHashMapTest {

    @Test
    public void testForeach() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(Integer.toString(i), i);
        }
        concurrentHashMap.forEach((k, v) -> {
            System.out.println("K:" + k + ";V:" + v);
        });
    }

    @Test
    public void testReduce() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= 5; i++) {
            concurrentHashMap.put(Integer.toString(i), i);
        }
        //并行计算map的总和
        //这里的2是并行数量
        // transformer函数是计算元素结果（K，V），reducer是元素之间的运算规则
        Integer count = concurrentHashMap.reduce(2,
                (k, v) -> Integer.valueOf(k) + v - 1, (value1, value2) -> value1 * value2);
        System.out.println(count);
    }

    @Test
    public void testReduceValues() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(Integer.toString(i), i);
        }
        //并行计算map的总和
        //这里的2是并行数量
        //reduceValues只对value进行运算
        Integer count = concurrentHashMap.reduceValues(2, Integer::sum);
        System.out.println(count);
    }

    /**
     * 测试map的computeIfAbsent方法
     * 1.若map存在指定key，直接返回;
     * 2.若map中不存在指定key，则先插入后返回;
     */
    @Test
    public void testComputeIfAbsent() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(Integer.toString(i), i);
        }
        Integer value = 100;
        //map中若存在该key，直接返回数据。若不存在该key，先put再返回。
        Integer count = concurrentHashMap.computeIfAbsent("100", v -> value);
        System.out.println(count);
    }

    /**
     * 测试search方法
     */
    @Test
    public void testSearch() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            concurrentHashMap.put(Integer.toString(i), i);
        }
        //这里的2是并行数
        String value = concurrentHashMap.search(2, (k, v) -> {
            //搜索逻辑
            if (v % 4 == 0) {
                return k + "---";
            }
            return null;
        });
        System.out.println(value);
    }


}
