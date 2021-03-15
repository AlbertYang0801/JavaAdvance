package com.albert.leetcode.array;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * @author Albert
 * @date 2021/3/14 上午1:09
 */
public class NumberTimes56Offer {

    public int[] singleNumbers(int[] nums) {
        //全部数字的异或结果
        int ret = 0;
        for (int num : nums) {
            ret ^= num;
        }
        //001
        int bit = 1;
        //从第一位开始与运算（010）
        while ((bit & ret) == 0) {
            //左移1位（010）
            bit <<= 1;
        }
        System.out.println(bit);
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((bit & num) != 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        NumberTimes56Offer numberTimes56Offer = new NumberTimes56Offer();
        int[] nums = {1, 2, 10, 4, 1, 4, 3, 3};
        int[] ints = numberTimes56Offer.singleNumbers(nums);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


}


/**
 * 异或操作
 * 相同数字的异或操作为0。
 * 对所有数据进行异或操作，得到的结果就是两个出现一次数字的异或结果。
 * 针对得到的结果分析，首位为1的位数。（当为1时说明两个值在这一位上不一样）
 * 比如：2和6 对应二进制为 010和110，异或结果为100，可以看出两者在第三位上不同。
 * 根据这一位对数据进行分组。
 * 分开的两组异或的结果就是数据的结果。
 */


/**
 * 两个不一样的值的异或操作。
 * 2和6 对应二进制为 010和110，异或结果为100
 * 异或结果第一个位1的位数，就是这两个值首先不一样的位数。
 */

