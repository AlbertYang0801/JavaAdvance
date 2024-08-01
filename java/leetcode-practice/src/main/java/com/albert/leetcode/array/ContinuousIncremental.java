package com.albert.leetcode.array;

/**
 * 674.最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 * @author yangjunwei
 * @date 2024/8/1
 */
public class ContinuousIncremental {

    public int continuousIncremental(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //备忘录
        int subScript = 1;

        //连续个数
        int j = 1;

        //上一个值
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //当前值和上一个值比较
            if (nums[i] > num) {
                j++;
                num = nums[i];
                //备忘录记录
                if (j > subScript) {
                    subScript = j;
                }
            } else {
                //备忘录记录
                if (j > subScript) {
                    subScript = j;
                }
                //初始化连续个数
                j = 1;
                num = nums[i];
            }
        }
        return subScript;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,2,3,4,5};
        int i = new ContinuousIncremental().continuousIncremental(nums);
        System.out.println(i);
    }


}
