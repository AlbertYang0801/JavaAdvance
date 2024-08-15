package com.albert.leetcode.dp;

import java.util.Arrays;

/**
 * 740. 删除并获得点数
 * <p>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class DeleteAndEarn740 {

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        //转换为打家劫舍解答
        int maxLength = Arrays.stream(nums).max().getAsInt() + 1;
        //初始化数组
        int[] table = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            table[i] = 0;
        }
        //将数组按照元素值转换为数组下标，并且累加。生成一个类似打家劫舍的数据
        //转换原始数组
        for (int i = 0; i < nums.length; i++) {
            int curr = table[nums[i]];
            curr += nums[i];
            table[nums[i]] = curr;
        }
        //转换后的数组类似
        // [2,2,3,3,3,4] -> [0,0,4,9,4]
        //此时相邻元素不能累加
        int[] dp = new int[table.length];
        dp[0] = table[0];
        dp[1] = Math.max(table[0], table[1]);
        for (int i = 2; i < table.length; i++) {
            //两种情况
            //前面两个元素+当前值
            //前面一个元素
            dp[i] = Math.max(dp[i - 2] + table[i], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 3, 3, 4};
        int i = new DeleteAndEarn740().deleteAndEarn(nums);
        System.out.println(i);
    }


}
