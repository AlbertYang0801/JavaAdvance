package com.albert.leetcode.array;

/**
 * Leetcode第724题：寻找数组的中心索引
 * 给你一个整数数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * 数组 中心索引 是数组的一个索引，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果数组不存在中心索引，返回 -1 。如果数组有多个中心索引，应该返回最靠近左边的那一个。
 * 注意：中心索引可能出现在数组的两端。
 *
 * @author Albert
 * @date 2021/2/21 上午1:20
 */
public class CentralIndex724 {

//    public static int pivotIndex(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            //左边
//            int a = 0;
//            for (int j = 0; j < i; j++) {
//                a += nums[j];
//            }
//            //右边
//            int b = 0;
//            for (int k = nums.length - 1; k > i; k--) {
//                b += nums[k];
//            }
//            //值相等输出，不相等下一个
//            if (a == b) {
//                return i;
//            }
//        }
//        return -1;
//    }

   public int pivotIndex(int[] nums) {
        int count = 0;
        for(int i=0;i<nums.length;i++){
            count +=nums[i];
        }
        int leftCount = 0;
        for(int i=0;i<nums.length;i++){
            if((count-nums[i])==leftCount*2){
                return i;
            }
            leftCount+=nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        CentralIndex724 centralIndex724 =new CentralIndex724();
        int[] nums = {-1,-1,-1,-1,-1,-1};
//        int[] nums = {1, 2, 3};
//        int[] nums = {2, 1, -1};
        int i = centralIndex724.pivotIndex(nums);
        System.out.println(i);
    }


}


/**
 * 解题思路：
 * 循环数组。
 * 当前值+左边值+右边值=count。
 * 1.计算数组总数。
 * 2.计算当前循环值左边的数据
 * 3.根据 count - 当前值 = 左边值*2
 * 4.若没找到，返回-1
 */
