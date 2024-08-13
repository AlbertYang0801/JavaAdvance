package com.albert.cache.lru.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap实现LRU算法
 * @author yjw
 * @date 2021/7/27 21:19
 */
public class LruCacheDemoJdk<K,V> extends LinkedHashMap<K,V> {

    //容量
    private int capacity;

    public LruCacheDemoJdk(int capacity){
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
        LruCacheDemoJdk<Integer,Integer> lruCacheDemoJdk = new LruCacheDemoJdk<Integer,Integer>(3);
        lruCacheDemoJdk.put(1,1);
        lruCacheDemoJdk.put(2,2);
        lruCacheDemoJdk.put(3,3);
        System.out.println(lruCacheDemoJdk.keySet());

        lruCacheDemoJdk.put(4,4);
        System.out.println(lruCacheDemoJdk.keySet());

        lruCacheDemoJdk.put(3,3);
        lruCacheDemoJdk.put(3,3);
        lruCacheDemoJdk.put(3,3);
        System.out.println(lruCacheDemoJdk.keySet());

        lruCacheDemoJdk.put(5,5);
        System.out.println(lruCacheDemoJdk.keySet());


    }




}
