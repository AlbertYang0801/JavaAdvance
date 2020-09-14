package com.albert.sort.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 希尔排序
 * 原理：直接插入排序的增强版
 *
 * @author yjw
 * @date 2020/9/14 1:00
 */
@Slf4j
public class ShellSort {

    public static int[] shellSort(int[] arr) {
        int Increment = arr.length ;
        int num = 1;

        while (true) {
            Increment = Increment / 2;
            log.info("第{}次排序，增量长度为{}", num, Increment);
            //增量长度（排序数据列表个数）
            for (int i = 0; i < Increment; i++) {
                log.info("第{}次将要进行插入排序的元素：",i+1);
                //选出每个数据列表，然后进行插入排序
                for (int j = 0; j < (arr.length/Increment)+1; j++) {
                    if(i+Increment*j>arr.length-1){
                        continue;
                    }
                    System.out.println(arr[i+Increment*j]);

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
        System.out.println(Arrays.toString(sort));
    }


}
