package com.albert.practice.eight.stream.utils;

/**
 * @author yangjunwei
 * @date 2024/7/24
 */
public class PrimeUtil {

    /**
     * 判断是否为质数
     */
    public static boolean isPrime(int num) {
        int temp = num;
        if (temp < 2) {
            return false;
        }
        for (int i = 2; Math.sqrt(temp) >= i; i++) {
            if (temp % i == 0) {
                return false;
            }
        }
        return true;
    }


}