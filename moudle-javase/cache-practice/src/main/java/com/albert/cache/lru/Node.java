package com.albert.cache.lru;

import lombok.Data;

/**
 * 结点类
 *
 * @author yjw
 */
@Data
public class Node<K, V> {

    K key;
    V value;
    //前驱
    Node<K, V> prev;
    //后继
    Node<K, V> next;

    public Node() {
        //初始化时，前驱和后继都是null
        this.prev = this.next = null;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        //初始化时，前驱和后继都是null
        this.prev = this.next = null;
    }

    public void printf() {
        System.out.println("node===> " + "key:" + key + " value:" + value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> currentNode = this;
        while (currentNode != null) {
            sb.append(currentNode.value).append(" -> ");
            currentNode = currentNode.next;
        }
        return sb.toString();
    }


}
