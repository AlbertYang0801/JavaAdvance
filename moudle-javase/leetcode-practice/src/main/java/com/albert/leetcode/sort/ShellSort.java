package com.albert.leetcode.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 希尔排序
 * 原理：直接插入排序的增强版
 * 现将整个待排记录序列分割成若干子序列，然后分别进行直接插入排序；待整个序列中的记录基本有序的时候，再对全体记录进行一次直接插入排序。
 * @author yjw
 * @date 2020/9/14 1:00
 */
@Slf4j
public class ShellSort {

    /**
     * 希尔排序：
     * 1.设置一个增量初值
     * 2.将增量值进行/2处理，直到增量值为<1,则不再处理
     * 3.根据增量值，进行对应次数的遍历
     * 4.每组根据增量值获取数据，并对数据进行直接插入排序
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int Increment = arr.length;
        int num = 1;
        int k;
        int temp;

        while (true) {
            Increment = Increment / 2;
            log.info("第{}次排序，增量长度为{}", num, Increment);
            //增量长度（排序数据列表个数）
            for (int i = 0; i < Increment; i++) {
                //选出每个数据列表，然后进行插入排序
                for (int j = i + Increment; j < arr.length; j = j + Increment) {
                    //未排序的第一个元素下标
                    k = j - Increment;
                    temp = arr[j];
                    //递归排序，从最后一个元素和前一个元素进行比较
                    while (k >= 0 && temp < arr[k]) {
                        //后面的元素和前面的元素替换位置
                        arr[k + Increment] = arr[k];
                        k = k - Increment;
                    }
                    //若不需要交换，则不替换数据
                    arr[k + Increment] = temp;
                }
            }
            log.info("第{}次进行排序后：{}", num, Arrays.toString(arr));
            num++;
            if (Increment == 1) {
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {50, 21, 7, 39, 32, 41, 18};
        int[] sort = shellSort(arr);
        log.info(Arrays.toString(sort));
    }


}
