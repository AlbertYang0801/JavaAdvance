package com.albert.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 498. 对角线遍历
 *
 * @author Albert
 * @date 2021/3/9 下午3:58
 */
public class DiagonalTraversal498 {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length <= 0) {
            return new int[]{};
        }
        //根据每个元素x和y的初始化集合
        List<List<Integer>> axisCount = new ArrayList<>();
        //x和y轴之后最大值
        int maxLength = matrix.length + matrix[0].length - 1;
        for (int i = 0; i < maxLength; i++) {
            //初始化集合
            axisCount.add(new ArrayList<>());
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //计算每个元素x和y的和
                int a = i + j;
                int value = matrix[i][j];
                //根据x和y轴的和获取list，存放当前value
                axisCount.get(a).add(value);
            }
        }
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < axisCount.size(); i++) {
            List<Integer> singleList = axisCount.get(i);
            //偶数按照插入顺序倒叙排列
            if (i % 2 == 0) {
                Collections.reverse(axisCount.get(i));
            }
            //奇数
            dataList.addAll(singleList);
        }
        int[] arr = new int[dataList.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = dataList.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
         int[][] matrix = {};
        DiagonalTraversal498 diagonalTraversal = new DiagonalTraversal498();
        int[] diagonalOrder = diagonalTraversal.findDiagonalOrder(matrix);
        for (int i : diagonalOrder) {
            System.out.println(i);
        }

    }


}


/**
 * 解题思路：
 * 每个对角线上的元素，x轴和y轴的和是一致的。
 * 将x轴和y轴和一样的数据按照从前往后的顺序存放到list里面。
 * 输出的时候，当x轴和y轴和为奇数时，从前往后输出；当x轴和y轴和为偶数时，从后往前输出；
 *
 * 1.确认x轴和y轴的和最大值，初始化一个集合axisCount，该集合元素是List<Integer>，里面包含了每个对角线从前往后的数据。
 * 2.循环二维数组，计算x+y的和，根据x+y的和从axisCount获取对应list，将当前循环值存放到list。
 * 3.循环结束后，循环集合axisCount，当下标为偶数时，对应list倒叙输出；下标为奇数时，正序输出。
 */
