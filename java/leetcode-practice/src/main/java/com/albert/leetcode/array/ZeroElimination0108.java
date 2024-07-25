package com.albert.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 01.08. 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * @author Albert
 * @date 2021/3/9 下午3:20
 */
public class ZeroElimination0108 {

    public void setZeroes(int[][] matrix) {

        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();

        //确认0的下标
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    xList.add(i);
                    yList.add(j);
                }
            }
        }

        //根据0的下标设置为0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //将x轴整行设置为0，将对应在y轴的元素设置为0
                if (xList.contains(i) || yList.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        //打印数据
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
            System.out.println("-------------------");
        }

    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        ZeroElimination0108 zeroElimination0108 = new ZeroElimination0108();
        zeroElimination0108.setZeroes(matrix);
    }


}

/**
 * 解题思路：
 * 第一次循环。确认0的下标，x和y轴分别保存在不同的list。
 * 第二次循环。根据0的下标，将对应元素设置为0。
 *      将x轴整行设置为0，将对应在y轴的元素设置为0
 */