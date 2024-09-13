package com.albert.leetcode.sort;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 分而治之
 * 选择一个基准值，比基准值小的放到左半区，比基准值大的放到右半区。
 * 接着处理左半区和右半区。
 *
 * @author yjw
 * @date 2020/9/14 22:27
 */
public class QuickSort {

    public static void quickSort(int[] arr, int begin, int end) {
        if (end - begin < 1) {
            return;
        }
        //划分左右半区
        int partition = partition(arr, begin, end);
        System.out.println(partition);
        //对左右半区进行快速排序
        quickSort(arr, begin, partition);
        quickSort(arr, partition + 1, end);
    }

    /**
     * 返回基准值位置
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    public static int partition(int[] arr, int begin, int end) {
        int left = begin;
        int right = end;
        //基准值
        int temp = arr[left];

        //对撞指针法
        //跳出循环时，left=right，且该位置为基准值位置
        while (left < right) {
            //右指针
            //以左边为基准，先移动右指针
            if (right > left && arr[right] >= temp) {
                right--;
            }
            //左指针
            if (right > left && arr[left] <= temp) {
                left++;
            }
            //满足左大右小时，交换元素位置
            if (arr[left] > temp && arr[right] < temp) {
                swap(arr, left, right);
            }
        }
        //交换基准值
        swap(arr, begin, left);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 9, 1};
        //左边元素为基准
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


}



