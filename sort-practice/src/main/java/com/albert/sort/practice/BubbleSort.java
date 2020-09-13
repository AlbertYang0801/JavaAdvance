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
     * 1.从第一个元素开始遍历，遍历次数为n-1
     * 2.若元素比后一位元素值大，则交换位置
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i-1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //交换元素位置
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            log.info("第{}次排序后：{}", i+1, Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {2,5,4,9,1};
        int[] ints = bubbleSort(arr);
        log.info(Arrays.toString(ints));
    }


}
