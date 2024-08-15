package com.albert.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120.三角形最小路径和
 * https://leetcode.cn/problems/triangle/description/?envType=study-plan-v2&envId=dynamic-programming
 *
 * @author yangjunwei
 * @date 2024/8/14
 */
public class MinimumTotal120 {

    //public int minimumTotal(List<List<Integer>> triangle) {
    //    if (triangle == null || triangle.size() == 0) {
    //        return 0;
    //    }
    //    if (triangle.size() == 1) {
    //        return triangle.get(0).get(0);
    //    }
    //
    //    //找出到达每个节点的最小值
    //
    //    // 2
    //    // 3 4
    //    // 6 5 7
    //    // 4 1 8 3
    //
    //    //到达某个节点的最小值
    //    // dp = min(nums[x,y-1],nums[x-1,y-1]
    //
    //    //如何处理边界
    //    //当前元素为最后一个的话，获取上个结合的倒数第二个元素即可
    //
    //    int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
    //
    //    dp[0][0] = triangle.get(0).get(0);
    //    //处理y轴边
    //    for (int i = 1; i < triangle.size(); i++) {
    //        dp[i][0] = triangle.get(i).get(0) + dp[i - 1][0];
    //    }
    //
    //    for (int i = 1; i < triangle.size(); i++) {
    //        for (int j = 1; j < triangle.get(i).size(); j++) {
    //            //当前行最后一个元素
    //            if (j == triangle.get(i).size() - 1) {
    //                dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
    //            } else {
    //                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
    //            }
    //        }
    //    }
    //    return Arrays.stream(dp[triangle.size() - 1]).min().getAsInt();
    //}

    //从倒数第一层开始查找
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        //找出到达每个节点的最小值

        // 2
        // 3 4
        // 6 5 7
        // 4 1 8 3

        //到达某个节点的最小值
        // dp = min(nums[x,y-1],nums[x-1,y-1]

        //如何处理边界
        //当前元素为最后一个的话，获取上个结合的倒数第二个元素即可

        int[] dp = new int[triangle.size()+1];
        //从倒数第一行开始查找
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();

        List<Integer> aList = new ArrayList<>();
        aList.add(2);
        List<Integer> bList = new ArrayList<>();
        bList.add(3);
        bList.add(4);

        List<Integer> cList = new ArrayList<>();
        cList.add(6);
        cList.add(5);
        cList.add(7);

        List<Integer> dList = new ArrayList<>();
        dList.add(4);
        dList.add(1);
        dList.add(8);
        dList.add(3);

        list.add(aList);
        list.add(bList);
        list.add(cList);
        list.add(dList);

        int i = new MinimumTotal120().minimumTotal(list);
        System.out.println(i);
    }


}
