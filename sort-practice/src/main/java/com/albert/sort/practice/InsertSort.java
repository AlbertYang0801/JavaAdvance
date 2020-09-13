package com.albert.sort.practice;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 直接插入排序
 * 原理：将数据按照顺序插入到表里。保证插入每个元素之后，表元素顺序是有序的。
 *
 * 实现方式：
 * 1.将传入数组的第一个元素视为顺序表的第一个元素
 * 2.从第二个元素开始和之前的元素进行比较
 * 3.比较规则是：若元素比比较的元素小，则两者交换位置
 * @author Albert
 * @date 2020/9/11 17:41
 */
@Slf4j
public class InsertSort {

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] sort(int[] arr) {
        //临时变量
        int temp;
        for (int i = 1; i < arr.length; i++) {
            //从第一个开始和之前的元素比较
            for(int j =0;j<i;j++){
                //若比之前的元素小，则交换元素
                if(arr[i]<arr[j]){
                    temp = arr[i];
                    arr[i]=arr[j];
                    arr[j] = temp;
                }
            }
            System.out.println("第"+i+"次排序后的结果:"+ Arrays.toString(arr));
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5,6,3,7,2};
        int[] sort = InsertSort.sort(arr);
        for (int i : sort) {
            System.out.println(i);
        }
    }


}
