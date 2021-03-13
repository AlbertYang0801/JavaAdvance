package com.albert.leetcode.array;


/**
 * 剑指 Offer 03. 数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * @author Albert
 * @date 2021/3/12 下午10:01
 */
public class RepeatNum {

    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // 当前值与数组下标一致
            if (nums[i] == i) {
                continue;
            }
            //获取当前值
            int a = nums[i];
            // 当前值对应的下标存在一样的值
            if (nums[a] == nums[i]) {
                return nums[i];
            }
            //将当前值替换到对应的下标处，如当前值为3，则替换到nums[3]的位置上去
            nums[i] = nums[a];
            nums[a] = a;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        RepeatNum repeatNum = new RepeatNum();
        int repeatNumber = repeatNum.findRepeatNumber(nums);
        System.out.println(repeatNumber);
    }


}


/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 。
 * 所以，可以根据数组下标来判断是否重复
 */