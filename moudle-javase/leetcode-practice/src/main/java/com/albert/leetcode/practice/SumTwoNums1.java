package com.albert.leetcode.practice;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * lettcode 1.计算两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 * 例：给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author Albert
 * @date 2020/11/12 12:01
 */
@Slf4j
public class SumTwoNums1 {

    /**
     * 暴力解法
     * 时间复杂度过高，o(n^2)
     */
//    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            //往后遍历匹配target-nums[i]
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[j] == target - nums[i]) {
//                    return new int[]{nums[i], nums[j]};
//                }
//            }
//        }
//        return new int[]{};
//    }

    public int[] twoSum(int[] nums, int target) {
        //使用Map进行存储数据，通过key值索引减少时间复杂度
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        SumTwoNums1 sumTwoNums1 = new SumTwoNums1();
        int[] ints = sumTwoNums1.twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


}
