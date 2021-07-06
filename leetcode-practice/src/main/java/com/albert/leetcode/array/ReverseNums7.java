package com.albert.leetcode.array;


import com.albert.utils.jackson.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @author Albert
 * @date 2021/3/14 下午2:44
 */
public class ReverseNums7 {

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        //符号位(0正1负)
        int sign = x > 0 ? 0 : 1;
        //记下符号位，将负数转换为正数
        x = x > 0 ? x : -x;

        //去除末尾的0（比如：100=》1）
        while (x % 10 == 0) {
            x = x / 10;
        }

        //桉顺序存放每位上的数据
        List<Integer> list = new ArrayList<>();
        while (x != 0) {
            list.add(x % 10);
            x = x / 10;
        }
        System.out.println(JsonUtil.toString(list));

        //将每一位的数据进行累加
        long temp = 0;
        for (int i = 0; i < list.size(); i++) {
            double pow = Math.pow(10.0, list.size() - i - 1);
            Integer num = list.get(i);
            temp += pow * num;
        }
        System.out.println(temp);
        //对反转后的结果进行判断，若x超过32位，返回0
        if (temp > (Math.pow(2.0, 31) - 1) || temp < (Math.pow(2.0, 31) * -1)) {
            return 0;
        }
        //添加符号位
        return (int) (sign == 0 ? temp : -temp);
    }

    public static void main(String[] args) {
        int x = 1534236469;
        ReverseNums7 reverseNums7 = new ReverseNums7();
        int reverse = reverseNums7.reverse(x);
        System.out.println(reverse);
    }


}


/**
 * 解题思路：
 * 1.若x为0，则返回0
 * 2.记录符号位，将x转换为正数
 * 3.获取数字每一位上的数字，保存到list
 * 4.将每一位的数据进行累加，实现数据反转
 * 5.对反转后的长度进行判断，超过32位返回0
 * 6.添加符号位
 */
