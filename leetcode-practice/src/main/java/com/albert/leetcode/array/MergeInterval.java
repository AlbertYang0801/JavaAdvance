package com.albert.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Leetcode第56题：合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * @author yjw
 * @date 2021/2/21 13:40
 */
public class MergeInterval {


    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        //将数组排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> list = new ArrayList<>();

        //获取初始左右边界
        int left = intervals[0][0];
        int right = intervals[0][1];

        //从第二个元素开始比较
        for (int i = 1; i < intervals.length; i++) {
            // 第二个元素与左边界值相等
            if (intervals[i][0] == left) {
                right = Math.max(right, intervals[i][1]);
            }
            // 第二个元素小于等于右边界值
            else if (intervals[i][0] <= right) {
                right = Math.max(right, intervals[i][1]);
            }
            // 不满足聚合条件，
            else {
                int[] temp = {left, right};
                list.add(temp);
                //将当前比较值设置为左右边界值
                left = intervals[i][0];
                right = intervals[i][1];
            }
            // 循环到最后，将左右边界值输出
            if (i == intervals.length - 1) {
                int[] temp = {left,right};
                list.add(temp);
            }
        }
        int[][] newIntervals = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            newIntervals[i] = list.get(i);
        }
        return newIntervals;
    }

    public static void main(String[] args) {
//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = {{1, 4}, {4, 5}};
//        int[][] intervals = {{1, 4}};
//        int[][] intervals = {{1, 4}, {0, 2}, {3, 5}};
        int[][] merge = MergeInterval.merge(intervals);
        for (int[] ints : merge) {
            System.out.println(ints[0] + "" + ints[1]);
        }
        System.out.println(merge.toString());
    }

}


//        示例 1：
//        输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//        输出：[[1,6],[8,10],[15,18]]
//        解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//        示例 2：
//
//        输入：intervals = [[1,4],[4,5]]
//        输出：[[1,5]]
//        解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。


//        1 <= intervals.length <= 104
//        intervals[i].length == 2
//        0 <= starti <= endi <= 104


