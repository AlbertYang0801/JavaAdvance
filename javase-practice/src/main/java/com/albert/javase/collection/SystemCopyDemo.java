package com.albert.javase.collection;


import cn.hutool.json.JSONUtil;

/**
 * @author Albert
 * @date 2021/3/23 下午6:40
 */
public class SystemCopyDemo {

    public static void main(String[] args) {
        int[] arr = new int[10];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        int[] newArr = new int[10];

        //源数组，源数组中开始拷贝的索引位置，目标数组，目标数组中开始复制的索引位置，执行需要复制的数据长度
        System.arraycopy(arr,2,newArr,3,3);
        newArr[2] = 90;
        System.out.println(JSONUtil.toJsonStr(newArr));
    }


}

/**
 * 将源数组，从指定索引位置开始复制，复制指定长度的数据。
 * 将复制的数据从执行索引位置放到新数组里，
 */
