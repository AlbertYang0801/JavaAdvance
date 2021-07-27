package com.albert.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yjw
 * @date 2021/7/27 21:19
 */
public class LruCacheDemo<K,V> extends LinkedHashMap<K,V> {

    //容量
    private int capacity;

    public LruCacheDemo(int capacity){
        //accessOrder:访问顺序（true，内部元素会按照访问顺序重新排列；false：内部元素不会按照访问顺序重新排列）
        //符合 LRU 算法时，新访问的数据要重新排列到队尾，以免提前出队。
        super(capacity,0.75F,false);
        this.capacity=capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size()>capacity;
    }

    public static void main(String[] args) {
        LruCacheDemo<Integer,Integer> lruCacheDemo = new LruCacheDemo<Integer,Integer>(3);

        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4,4);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3,3);
        lruCacheDemo.put(3,3);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(5,5);
        System.out.println(lruCacheDemo.keySet());
    }




}
