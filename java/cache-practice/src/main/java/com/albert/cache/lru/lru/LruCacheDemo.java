package com.albert.cache.lru.lru;

import com.albert.cache.lru.DoubleLinkedList;
import com.albert.cache.lru.Node;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public LruCacheDemo(int cacheSize) {
        this.cacheSize = cacheSize;
        this.map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(Integer key) {
        //不存在
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        //调整访问节点的位置
        doubleLinkedList.remove(node);
        doubleLinkedList.addHead(node);
        return node.getValue();
    }

    public void put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            Node<Integer, Integer> oldNode = map.get(key);
            oldNode.setValue(value);
            //调整更新元素的位置
            doubleLinkedList.remove(oldNode);
            doubleLinkedList.addHead(oldNode);
            return;
        }
        if (map.size() >= cacheSize) {
            //缓存已满，淘汰最近最久未使用的元素
            Node<Integer, Integer> tailNode = doubleLinkedList.getHead();
            map.remove(tailNode.getKey());
            doubleLinkedList.remove(tailNode);
        }
        //新插入元素
        Node<Integer, Integer> newNode = new Node<>(key, value);
        map.put(key, newNode);
        doubleLinkedList.addHead(newNode);
    }

    //获取排序key列表
    private List<Integer> sortKeyList() {
        List<Integer> list = Lists.newArrayList();
        //获取第一个结点
        Node<Integer, Integer> node = doubleLinkedList.getHead().getNext();
        while (node.getNext() != null) {
            //匹配到尾指针，结束
            if (node == doubleLinkedList.getTail()) {
                break;
            }
            list.add(node.getKey());
            //遍历下一个结点
            node = node.getNext();
        }
        return list;
    }

    public static void main(String[] args) {
        LruCacheDemo lruCacheDemo = new LruCacheDemo(3);

        lruCacheDemo.put(1, 1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(4, 4);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(3, 3);
        lruCacheDemo.put(3, 3);
        lruCacheDemo.put(3, 3);
        System.out.println(lruCacheDemo.sortKeyList());

        lruCacheDemo.put(5, 5);
        System.out.println(lruCacheDemo.sortKeyList());
    }


}

