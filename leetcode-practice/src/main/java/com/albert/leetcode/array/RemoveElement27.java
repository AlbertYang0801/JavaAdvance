package com.albert.leetcode.array;

/**
 * 27. 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author Albert
 * @date 2021/3/14 下午3:56
 */
public class RemoveElement27 {

    /**
     * 返回移除元素后的数组长度
     */
    public int removeElement(int[] nums, int val) {
        //慢指针，指向不符合的数据
        int slowIndex = 0;
        //快指针
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                //将不符合的数据，按顺序放到数组里
                nums[slowIndex] = nums[i];
                //慢指针加1
                slowIndex++;
            }
        }
        return slowIndex;
    }

    public static void main(String[] args) {
        RemoveElement27 removeElement27 = new RemoveElement27();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int i = removeElement27.removeElement(nums, 2);
        System.out.println(i);

        System.out.println("-----");
        for (int j = 0; j < nums.length; j++) {
            System.out.println(nums[j]);
        }
    }


}

/**
 * 采用数组的双指针法
 * 快慢指针法
 * 第一个指针指向数组元素，第二个指针指向不等于val的值
 * 按顺序循环，将不符合的数据按照顺序放到数组里，第二个指针累加的结果即为不符合的长度。
 */