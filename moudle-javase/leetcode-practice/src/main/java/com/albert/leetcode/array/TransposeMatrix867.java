package com.albert.leetcode.array;

/**
 * 867. 转置矩阵
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 * <p>
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * @author Albert
 * @date 2021/3/23 下午12:32
 */
public class TransposeMatrix867 {

    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //创建新数组
        int[][] newMatrix = new int[n][m];
        //循环新数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //赋值
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args) {
//        int[][] matrix = {{2, 4, -1}, {-10, 5, 11}, {18, -7, 6}};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        TransposeMatrix867 transposeMatrix867 = new TransposeMatrix867();
        int[][] transpose = transposeMatrix867.transpose(matrix);
        for (int i = 0; i < transpose.length; i++) {
            for (int j = 0; j < transpose[i].length; j++) {
                System.out.println(transpose[i][j]);
            }
            System.out.println("----");
        }
    }

}
