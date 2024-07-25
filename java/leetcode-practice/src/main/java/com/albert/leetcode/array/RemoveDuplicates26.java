package com.albert.leetcode.array;

/**
 * 26. 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * @author Albert
 * @date 2021/3/14 下午4:52
 */
public class RemoveDuplicates26 {

    public int removeDuplicates(int[] nums) {
        //双指针法
        int slowIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            //若是新元素
            if(nums[slowIndex]!=nums[i]){
                //慢指针加1
                slowIndex++;
                //添加新元素
                nums[slowIndex] = nums[i];
            }
        }
        return slowIndex+1;
    }

    public static void main(String[] args) {
        RemoveDuplicates26 removeDuplicates26 = new RemoveDuplicates26();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int i = removeDuplicates26.removeDuplicates(nums);
        System.out.println(i);
        for(int j=0;j<nums.length;j++){
            System.out.println(nums[j]);
        }
    }


}
