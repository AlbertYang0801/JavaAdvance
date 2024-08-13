package com.albert.leetcode.dp;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class ClimbStairs70 {

    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int[] nums = new int[n + 1];
        //动态规划
        //分解子问题
        //num[i] = nums[i-1]+nums[i-2]
        //假如上到 i-1 个台阶有m种方法，i-2个台阶有n种方法。i-1到i位置只需要一步，i-2到i位置需要两步。而且是固定的步数，所以到达i的方法为 nums[i-1] + nums[i-2]
        nums[1] = 1;
        nums[2] = 2;
        for (int i = 3; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
            if (n == i) {
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int i = new ClimbStairs70().climbStairs(3);
        System.out.println(i);
        int n = new ClimbStairs70().climbStairs(4);
        System.out.println(n);
    }


}
