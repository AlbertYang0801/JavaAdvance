package com.albert.javase.collection;

import cn.hutool.json.JSONUtil;

import java.util.Arrays;

/**
 * @author Albert
 * @date 2021/3/23 下午6:52
 */
public class ArraysCopyDemo {

    public static void main(String[] args) {
        int[] arr= new int[5];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        //对数组进行内部扩容（实际调用了System.arraycopy方法）
        int[] newArr = Arrays.copyOf(arr, 3);
        System.out.println(JSONUtil.toJsonStr(newArr));
    }


}
