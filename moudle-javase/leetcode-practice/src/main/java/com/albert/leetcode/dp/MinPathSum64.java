package com.albert.leetcode.dp;

/**
 * 64. 最小路径和
 * https://leetcode.cn/problems/minimum-path-sum/?envType=study-plan-v2&envId=dynamic-programming
 *
 * @author yangjunwei
 * @date 2024/8/14
 */
public class MinPathSum64 {

    public int minPathSum(int[][] grid) {
        //二维数组， nums[m,n] = nums[m-1,n]+ nums[m,n-1]
        //每个单元格有左边和上面两条路。每条路有对应的步数
        int y = grid.length;
        int x = grid[0].length;

        //二维数组
        int[][] dp = new int[y][x];
        dp[0][0] = grid[0][0];

        //初始化边
        for (int i = 1; i < x; i++) {
            //当前值+左边值
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        //y轴
        for (int i = 1; i < y; i++) {
            //当前值+上边值
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                //比较左边和上边的值
                int pre = Math.min(dp[i][j - 1], dp[i - 1][j]);
                dp[i][j] = pre + grid[i][j];
            }
        }
        return dp[y - 1][x - 1];
    }

    public static void main(String[] args) {
        int i = new MinPathSum64().minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});
        System.out.println(i);
    }


}
