package com.albert.cache.lru.slru;


import com.albert.cache.lru.Node;

/**
 * 保护段
 * 基于LRU
 *
 * @author yjw
 */
public class ProtectAbstractStage<K, V> extends AbstractStageCache<K, V> {

    public ProtectAbstractStage(int cacheSize) {
        super(cacheSize);
    }

    @Override
    public void put(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        //更新
        if (dataMap.containsKey(key)) {
            Node<K, V> kvNode = dataMap.get(key);
            kvNode.setValue(value);
            return;
        }
        dataMap.put(key, node);
        //保存到双向链表头部
        linkedList.addFirst(node);
    }

    /**
     * 缓存不足，会进行缓存淘汰。
     * 将淘汰的数据放入淘汰段
     *
     * @param key
     * @param value
     */
    @Override
    public Node<K, V> putDailOut(K key, V value) {
        Node<K, V> tail = null;
        //更新
        if(dataMap.containsKey(key)){
            Node<K, V> kvNode = dataMap.get(key);
            kvNode.setValue(value);
            return tail;
        }
        //校验缓存长度，淘汰最久未使用的数据
        if (this.isFull()) {
            //淘汰段直接淘汰数据
            tail = linkedList.getLast();
            this.remove(tail.getKey());
        }
        this.put(key, value);
        //返回淘汰的数据
        return tail;
    }

    @Override
    public boolean remove(K key) {
        //check key
        if (dataMap.containsKey(key)) {
            Node<K, V> node = dataMap.get(key);
            linkedList.remove(node);
            dataMap.remove(key);
            accessCountMap.remove(key);
        }
        return true;
    }

    @Override
    public V get(K key) {
        if (!dataMap.containsKey(key)) {
            return null;
        }

        Node<K, V> node = dataMap.get(key);
        //基于LRU调整节点位置
        toHead(node);

        //不考虑晋升的问题，只计数
        Integer countValue = accessCountMap.getOrDefault(key, 0);
        accessCountMap.putIfAbsent(key, countValue + 1);

        return node.getValue();
    }




}
