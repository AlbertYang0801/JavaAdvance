package com.albert.cache.lru.slru;


import com.albert.cache.lru.Node;

import java.util.Objects;

/**
 * @author yjw
 */
public class SlruCache<K, V> {

    //保护段
    private final ProtectAbstractStage<K, V> protectStage;

    //淘汰段
    private final EliminationAbstractStage<K, V> eliminationStage;

    public SlruCache(int protectStageCacheSize, int eliminationStageCacheSize) {
        this.protectStage = new ProtectAbstractStage<>(protectStageCacheSize);
        this.eliminationStage = new EliminationAbstractStage<>(eliminationStageCacheSize);
    }

    /**
     * 新增缓存
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        //先添加到淘汰段,考虑LRU淘汰
        eliminationStage.putDailOut(key, value);
    }

    /**
     * 查询缓存
     *
     * @param key
     * @return
     */
    public V get(K key) {
        //访问淘汰段
        if (eliminationStage.containsKey(key)) {
            V v = eliminationStage.get(key);
            int accessCount = eliminationStage.getAccessCount(key);
            //访问次数>1 晋升保护段
            if (accessCount > 1) {
                //淘汰段晋升保护段
                promotion(key, v);
            }
            return v;
        }

        //访问保护段
        if (protectStage.containsKey(key)) {
            return protectStage.get(key);
        }

        return null;
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return
     */
    public boolean removeCache(K key) {
        //判断缓存是在淘汰段还是保护段
        if (eliminationStage.containsKey(key)) {
            return eliminationStage.remove(key);
        }
        if (protectStage.containsKey(key)) {
            return protectStage.remove(key);
        }
        return true;
    }


    public void promotion(K key, V value) {
        eliminationStage.remove(key);
        //晋升保护段
        //如果保护段满了，需要先淘汰元素到淘汰段
        if (protectStage.isFull()) {
            //淘汰保护段元素到淘汰段
            Node<K, V> lastNode = protectStage.putDailOut(key, value);
            if(Objects.isNull(lastNode)){
                return;
            }
            //淘汰段存放数据，支持LRU
            eliminationStage.putDailOut(lastNode.getKey(), lastNode.getValue());
        } else {
            //直接放入保护段数据
            protectStage.put(key, value);
        }
    }

    public void printf() {
        System.out.println("-------淘汰段---------");
        System.out.println(eliminationStage.linkedList.toString());

        System.out.println("-------保护段---------");
        System.out.println(protectStage.linkedList.toString());
    }

    public static void main(String[] args) {
        SlruCache<Integer, Integer> slruCache = new SlruCache<>(3, 3);
        slruCache.add(1, 1);
        slruCache.add(2, 2);
        slruCache.printf();
    }


}
