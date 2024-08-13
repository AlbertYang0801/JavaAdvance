package com.albert.cache.lru.slru;


import com.albert.cache.lru.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yjw
 */
public abstract class AbstractStageCache<K, V> {

    //哈希表，保存数据。方便查找，解决链表查找慢的问题。
    public Map<K, Node<K, V>> dataMap = new HashMap<>();

    //双向链表，只用来保存元素顺序
    public LinkedList<Node<K, V>> linkedList = new LinkedList<>();

    public Map<K, Integer> accessCountMap = new HashMap<>();

    public int cacheSize;

    public AbstractStageCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    /**
     * 调整到队头
     *
     * @param node
     */
    public void toHead(Node<K, V> node) {
        if (dataMap.containsKey(node.getKey())) {
            linkedList.remove(node);
            linkedList.addFirst(node);
        }
    }

    public int getAccessCount(K key) {
        return accessCountMap.getOrDefault(key, 0);
    }

    public boolean isFull() {
        return dataMap.size() >= cacheSize;
    }

    public boolean containsKey(K key) {
        return dataMap.containsKey(key);
    }

    /**
     * 只put
     *
     * @param key
     * @param value
     */
    public abstract void put(K key, V value);

    /**
     * 缓存不足，会进行缓存淘汰
     *
     * @param key
     * @param value
     * @return 淘汰的数据
     */
    public abstract Node<K, V> putDailOut(K key, V value);

    public abstract boolean remove(K key);

    public abstract V get(K key);


}
