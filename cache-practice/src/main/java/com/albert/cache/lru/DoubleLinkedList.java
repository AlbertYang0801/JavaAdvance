package com.albert.cache.lru;

import lombok.Data;

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
        //单节点，设置head
        if (Objects.isNull(head)) {
            head = node;
            tail = node;
            return;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    //删除结点
    public void remove(Node<K, V> node) {
        //处理prev
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            //是head节点
            head = node.next;
        }

        //处理next
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            //是tail节点
            tail = node.prev;
        }
    }

    //获取第一个结点
    public Node<K, V> getHead() {
        return head;
    }

    /**
     * 从头节点遍历链表
     */
    public void printf() {
        Node<K, V> node = head;
        while (Objects.nonNull(node)) {
            node.printf();
            node = node.getNext();
        }
    }

}