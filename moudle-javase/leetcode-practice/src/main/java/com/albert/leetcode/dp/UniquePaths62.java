package com.albert.leetcode.dp;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * 输入：m = 3, n = 2
 * 输出：3
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class UniquePaths62 {

    public int uniquePaths(int m, int n) {
        //每个单元格有左边和上面两条路。每条路有对应的步数

        //二维数组
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        //初始化边
        //边上的走法都是1
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = 1;
        }

        //二维数组遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //二维数组， nums[m,n] = nums[m-1,n]+ nums[m,n-1]
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int i = new UniquePaths62().uniquePaths(3, 7);
        System.out.println(i);
    }


}
