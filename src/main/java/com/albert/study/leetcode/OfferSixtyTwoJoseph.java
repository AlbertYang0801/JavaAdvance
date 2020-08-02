package com.albert.study.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode题目:
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 题目对应：约瑟夫环
 * @author Albert
 * @date 2020/8/2 17:54
 */
@Slf4j
public class OfferSixtyTwoJoseph {

    public static int lastRemaining(int n, int m) {
        //创建list
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            //获取指定索引
            index = (index - 1 + m) % list.size();
            //删除指定索引元素
            list.remove(index);
            n--;
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        int i = OfferSixtyTwoJoseph.lastRemaining(10, 17);
        log.info("约瑟夫环的结果值:{}",i);
    }


}


//3

//0 1 2 3 4
//删除2

// 0 1 3 4
//删除0

// 1 3 4
//删除4

// 1 3
//删除1

// 3


/**
 * 解题思路：
 * 1.使用list集合保存元素
 * 2.计算删除元素索引的位置，根据规律总结
 * 3.删除list中的指定元素(选择方法，考虑到时间复杂度问题)
 *
 * 碰到的问题：
 * list的remove()方法:
 *      list有两个remove()方法，分别是：
 *          remove(int index)   =>  删除指定索引对应的元素
 *          remove(Objcet object)   =>  删除值对应的元素
 *      传入的Integer类型，对应的是第二个方法，不是根据索引删除，所以索引对应的类型应该选用int类型，而不是Integer类型。
 */

