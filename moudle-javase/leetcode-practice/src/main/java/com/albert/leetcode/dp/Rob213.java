package com.albert.leetcode.dp;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <p>
 * <p>
 * 首尾相连
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class Rob213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        //环问题
        //把循环拆成两个队列
        //一个是 0～n-1
        //一个是 1～n
        int[] numA = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] numB = Arrays.copyOfRange(nums, 1, nums.length);

        int a = singleRob(numA);
        int b = singleRob(numB);
        return Math.max(a, b);
    }

    private int singleRob(int[] nums) {
        //每个位置上最多能偷到的钱
        //前一个位置能偷到的钱 或者 前两个位置加上当前位置的钱
        // dp = max(dp[i-1],dp[i-2]+nums[i] )
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 1};
        int rob = new Rob213().rob(nums);
        System.out.println(rob);
    }


}
