package com.albert.leetcode.array;

/**
 * LeetCode第48题
 * 将矩阵旋转90度
 * @author Albert
 * @date 2021/2/22 下午9:05
 */
public class RotationMatrix48 {

    public static void rotate(int[][] matrix) {
        //镜像翻转
        //确认中间值
        int[] temps;
        for (int i = 0; i < matrix.length / 2; i++) {
            temps = matrix[i];
            matrix[i] = matrix[matrix.length-1-i];
            matrix[matrix.length-1-i]= temps;
        }

        int temp;
        //对角线翻转
        for(int i=0;i<matrix.length;i++){
            for(int j=i+1;j<matrix.length;j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]= temp;
            }
        }

    }

    public static void main(String[] args) {
//        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{5,1,9,11}, {2,4,8,10}, {13,3,6,7},{15,14,12,16}};
        rotate(matrix);


        for (int[] ints : matrix) {
            for (int a : ints) {
                System.out.println(a);
            }
            System.out.println("--------");
        }
    }


}


//    给定 matrix =
//        [
//        [1,2,3],
//        [4,5,6],
//        [7,8,9]
//        ],
//
//        原地旋转输入矩阵，使其变为:
//        [
//        [7,4,1],
//        [8,5,2],
//        [9,6,3]
//        ]

