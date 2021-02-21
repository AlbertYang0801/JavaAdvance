package com.albert.leetcode.array;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * @author yjw
 * @date 2021/2/21 13:40
 */
public class SolutionC {


    public int[][] merge(int[][] intervals) {
        //根据starti排序


        //插入排序
        int[][] newArrays = new int[intervals.length][];

        for(int i=0;i<intervals.length;i++){
            //当前值
            int[] interval = intervals[i];

            //当前值与原数组所有值比较
            newArrays[i] = interval;



        }


        return null;
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


