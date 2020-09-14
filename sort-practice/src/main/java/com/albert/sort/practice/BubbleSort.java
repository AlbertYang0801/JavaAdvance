package com.albert.sort.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 冒泡排序
 * 原理：从第一个元素开始，和相邻的元素进比较，若前面的元素比后面的元素大，则两者交换位置，直到最大的元素放到最后位置。
 *
 * @author yjw
 * @date 2020/9/14 0:32
 */
@Slf4j
public class BubbleSort {

    /**
     * 冒泡排序：
     * 1.外层循环n-1次，代表需要循环n-1次
     * 2.内层循环：比较为排序的字段列表，相邻元素进行比较，大的元素排在后面
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //对未排序的列表进行排序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换元素位置
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            log.info("第{}次排序后：{}", i + 1, Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 9, 1};
        int[] ints = bubbleSort(arr);
        log.info(Arrays.toString(ints));
    }


}
