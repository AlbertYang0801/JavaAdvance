package com.albert.leetcode.dp;

/**
 * 63. 不同路径 II
 * https://leetcode.cn/problems/unique-paths-ii/description/?envType=study-plan-v2&envId=dynamic-programming
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class UniquePathsWithObstacles63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        //二维数组， nums[m,n] = nums[m-1,n]+ nums[m,n-1]
        //每个单元格有左边和上面两条路。每条路有对应的步数

        int y = obstacleGrid.length;
        int x = obstacleGrid[0].length;

        //二维数组
        int[][] dp = new int[y][x];
        //左上角也可能有障碍物
        if (obstacleGrid[0][0] == 1) {
            dp[0][0] = 0;
        } else {
            dp[0][0] = 1;
        }

        //初始化边
        //x轴
        for (int i = 1; i < x; i++) {
            //当前或者左边元素
            int tmp = obstacleGrid[0][i];
            //判断自身是否有障碍物
            if (tmp == 1) {
                dp[0][i] = 0;
            } else {
                //x边上的走法按照左边的
                dp[0][i] = dp[0][i - 1];
            }
        }
        //y轴
        for (int i = 1; i < y; i++) {
            //当前或者上边元素
            int tmp = obstacleGrid[i][0];
            //判断自身是否有障碍物
            if (tmp == 1) {
                dp[i][0] = 0;
            } else {
                //y轴依照上个节点
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int i = 1; i < y; i++) {
            for (int j = 1; j < x; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[y - 1][x - 1];
    }

    public static void main(String[] args) {
        //int i = new UniquePathsWithObstacles63().uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
        //System.out.println(i);
        int n = new UniquePathsWithObstacles63().uniquePathsWithObstacles(new int[][]{{0, 0}, {1, 1}, {0, 0}});
        System.out.println(n);
        //int m = new UniquePathsWithObstacles63().uniquePathsWithObstacles(new int[][]{{0}, {1}});
        //System.out.println(m);
    }


}
