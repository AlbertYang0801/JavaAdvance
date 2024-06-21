package com.albert.cache.lru;

import lombok.Data;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 双向链表
 * 不支持并发安全
 *
 * @param <K>
 * @param <V>
 * @author yjw
 */
@Data
public class DoubleLinkedList<K, V> {

    //头节点
    Node<K, V> head;
    //尾节点
    Node<K, V> tail;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void add(Node<K, V> node) {
        //新元素添加到头部
        this.addHead(node);
    }

    public void addHead(Node<K, V> node) {
        Node<K, V> f = head;
        Node<K, V> newNode = node;
        head = newNode;
        if (f == null)
            tail = newNode;
        else
            f.prev = newNode;
    }

    //删除结点
    public void remove(Node<K, V> node) {
        // assert x != null;
        final Node<K,V> next = node.next;
        final Node<K,V> prev = node.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        //gc
        node.key=null;
        node.value=null;
    }

    //获取第一个结点
    public Node<K, V> getHead() {
        return head;
    }


}