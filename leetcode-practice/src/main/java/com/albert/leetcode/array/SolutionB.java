package com.albert.leetcode.array;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * @author Albert
 * @date 2021/2/21 上午1:41
 */
public class SolutionB {

//    public static int searchInsert(int[] nums, int target) {
//        if(nums.length<=0){
//            return 0;
//        }
//        //折半查找
//        int middle = nums.length / 2;
//        int middleNum = nums[middle];
//        if(middleNum==target){
//            return middle;
//        }
//        //左边查找
//        if(middleNum>target){
//            for(int j=0;j<=middle;j++){
//                if(target<=nums[j]){
//                    return j;
//                }
//            }
//        }
//        //右边查找
//        for(int j=middle+1;j<nums.length;j++){
//            if(target<=nums[j]){
//                return j;
//            }
//        }
//        return nums.length;
//    }

    public static int searchInsert(int[] nums, int target) {
        if (nums.length <= 0) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            //若中间值=目标值，返回中间值下标
            if (nums[middle] == target) {
                return middle;
            }
            //若最小值>目标值，则目标值将插入到最小值的位置，返回最小值的下标
            if(nums[low]>target){
                return low;
            }
            //若最大值<目标值，则目标值将插入到最大值后一位。
            if(nums[high]<target){
                return high+1;
            }
            //左边查找（中间值不再查找）
            if (nums[middle] > target) {
                high = middle-1;
            }
            //右边查找（中间值不再查找）
            if (nums[middle] < target) {
                low = middle+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,7};
        int target = 6;
        int i = SolutionB.searchInsert(nums, target);
        System.out.println(i);
    }


    /**
     * 二分查找实现
     */

}
