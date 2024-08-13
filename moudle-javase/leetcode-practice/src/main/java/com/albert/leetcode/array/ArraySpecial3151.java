package com.albert.leetcode.array;

/**
 * 如果数组的每一对相邻元素都是两个奇偶性不同的数字，则该数组被认为是一个 特殊数组 。
 * <p>
 * Aging 有一个整数数组 nums。如果 nums 是一个 特殊数组 ，返回 true，否则返回 false。
 *
 * @author yangjunwei
 * @date 2024/8/13
 */
public class ArraySpecial3151 {

    /**
     * 备忘录模式遍历
     *
     * @param nums
     * @return
     */
    public boolean isArraySpecial(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        //备忘录
        Boolean isEvenNumber = isEvenNumber(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            //当前奇偶性
            Boolean curr = isEvenNumber(nums[i]);
            if (isEvenNumber.equals(curr)) {
                return false;
            }
            isEvenNumber = curr;
        }
        return true;
    }

    /**
     * 偶数
     *
     * @param num
     * @return
     */
    private Boolean isEvenNumber(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,1,6};
        boolean arraySpecial = new ArraySpecial3151().isArraySpecial(nums);
        System.out.println(arraySpecial);
    }


}
