package com.albert.leetcode.dp;

/**
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class MinCostClimbingStairs746 {

    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        if (cost.length == 1) {
            return cost[0];
        }
        //到达该位置花钱，和该位置的价格
        //dp=min(nums[i-1]+dp(i-1),nums[i-2]+dp(i-2))

        //比如 10,15,20的情况，对应的 dp = {0,0,10}，分别对应每个位置的花费
        //但是要到顶就要计算，从15和20出发谁的花费少。对应的 dp = {0,0,10,15} 。也就是到顶最少需要15
        //min(nums[2]+dp[2],nums[1]+dp[1]) = min(30, 15) = 15

        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        //在循环到达数组长度时候继续比较，计算最后到顶的价格。
        for (int i = 2; i <= cost.length; i++) {
            //上一个位置花费 = 上个位置起步价格+到达上一个位置花费
            int a = cost[i - 1] + dp[i - 1];
            //上上一个位置
            int b = cost[i - 2] + dp[i - 2];
            //到达i的最小花费
            dp[i] = Math.min(a, b);
        }
        //返回到达顶层的花费
        return dp[cost.length];
    }

    public static void main(String[] args) {
        //int[] cost = new int[]{10, 15, 20};
        int[] cost = new int[]{1,100,1,1,1,100,1,1,100,1};
        int i = new MinCostClimbingStairs746().minCostClimbingStairs(cost);
        System.out.println(i);
    }


}
