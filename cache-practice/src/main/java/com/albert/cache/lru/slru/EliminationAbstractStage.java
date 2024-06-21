package com.albert.cache.lru.slru;


import com.albert.cache.lru.Node;

/**
 * 淘汰段
 * 基于LRU，需要对元素访问次数进行计数
 *
 * @author yjw
 */
public class EliminationAbstractStage<K, V> extends AbstractStageCache<K, V> {

    public EliminationAbstractStage(int cacheSize) {
        super(cacheSize);
    }

    @Override
    public V get(K key) {
        if (!dataMap.containsKey(key)) {
            return null;
        }
        //基于LRU调整节点顺序到队头
        Node<K, V> node = dataMap.get(key);
        toHead(node);

        //不考虑晋升的问题，只计数
        Integer countValue = accessCountMap.getOrDefault(key, 0);
        accessCountMap.put(key, countValue + 1);

        return node.getValue();
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
    public void put(K key, V value) {
        if (dataMap.containsKey(key)) {
            Node<K, V> kvNode = dataMap.get(key);
            kvNode.setValue(value);
            return;
        }
        Node<K, V> node = new Node<>(key, value);
        //保存到hash表
        dataMap.put(key, node);
        //保存到双向链表头部
        linkedList.addFirst(node);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Node<K, V> putDailOut(K key, V value) {
        Node<K, V> tail = null;
        if (dataMap.containsKey(key)) {
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


}
