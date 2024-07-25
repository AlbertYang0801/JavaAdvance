package com.albert.leetcode.array;

/**
 * Leetcode第35题
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * @author Albert
 * @date 2021/2/21 上午1:41
 */
public class InsertPosition35 {

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
            if (i == nums.length - 1) {
                return i + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        InsertPosition35 insertPosition35 = new InsertPosition35();
        int[] nums = {1, 3, 4, 7};
        int target = 6;
        int i = insertPosition35.searchInsert(nums, target);
        System.out.println(i);
    }


}

/**
 * 实现思路：
 * 循环遍历数组，target若<=当前值，返回当前值下标。
 * 若循环结束仍没有找到，则返回数组长度+1；
 */
