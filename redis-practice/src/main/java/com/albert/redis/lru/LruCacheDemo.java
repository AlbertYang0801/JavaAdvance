package com.albert.redis.lru;

import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yjw
 * @date 2021/7/28 20:15
 */
public class LruCacheDemo {

    private int cacheSize;
    //哈希表，保存元素内容
    private Map<Integer, Node<Integer, Integer>> map;
    //双向链表，保存元素顺序
    private DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LruCacheDemo(int cacheSize){
        this.cacheSize=cacheSize;
        this.map=new HashMap<>();
        doubleLinkedList=new DoubleLinkedList<>();
    }

    public int get(Integer key) {
        //不存在
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        //调整访问节点的位置
        doubleLinkedList.remove(node);
        doubleLinkedList.addTail(node);
        return node.value;
    }

    public void put(Integer key, Integer value) {
        if(map.containsKey(key)){
            Node<Integer, Integer> oldNode = map.get(key);
            oldNode.value=value;
            //调整更新元素的位置
            doubleLinkedList.remove(oldNode);
            doubleLinkedList.addTail(oldNode);
            return;
        }
        if(map.size()>=cacheSize){
            //缓存已满，淘汰最近最久未使用的元素
            Node<Integer, Integer> tailNode = doubleLinkedList.getHead();
            map.remove(tailNode.key);
            doubleLinkedList.remove(tailNode);
        }
        //新插入元素
        Node<Integer,Integer> newNode = new Node<>(key,value);
        map.put(key,newNode);
        doubleLinkedList.addTail(newNode);
    }

    //获取排序key列表
    private List<Integer> sortKeyList(){
        List<Integer> list = Lists.newArrayList();
        //获取第一个结点
        Node<Integer, Integer> node = doubleLinkedList.head.next;
        while (node.next!=null){
            //匹配到尾指针，结束
            if(node==doubleLinkedList.tail){
                break;
            }
            list.add(node.value);
            //遍历下一个结点
            node=node.next;
        }
        return list;
    }

    public static void main(String[] args) {
        LruCacheDemo lruCacheDemo = new LruCacheDemo(3);

        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(4,4);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(3,3);
        lruCacheDemo.put(3,3);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(5,5);
        System.out.println(lruCacheDemo.sortKeyList());
    }


}

/**
 * 结点类
 */
class Node<K, V> {
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

}

/**
 * 双向链表
 */
class DoubleLinkedList<K, V> {
    Node<K, V> head;
    Node<K, V> tail;

    public DoubleLinkedList() {
        //头尾节点都为空
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.prev = head;
    }

    //添加到尾部
    public void addTail(Node<K, V> node) {
        node.next=tail;
        node.prev= tail.prev;
        tail.prev.next = node;
        tail.prev= node;
    }

    //删除结点
    public void remove(Node<K, V> node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = null;
        node.next = null;
    }

    //获取第一个结点
    public Node<K, V> getHead() {
        return head.next;
    }


}