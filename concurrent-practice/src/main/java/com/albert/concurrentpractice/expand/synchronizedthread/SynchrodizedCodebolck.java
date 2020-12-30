package com.albert.concurrentpractice.expand.synchronizedthread;

import java.math.BigDecimal;

/**
 * 同步代码块
 *
 * @author Albert
 * @date 2020/12/29 下午5:10
 */
public class SynchrodizedCodebolck {

    public static void main(String[] args) {
        String a= "2.0277104E8";
        BigDecimal bigDecimal  = new BigDecimal(a);
        System.out.println(bigDecimal.toPlainString());

    }


}


/**
 * 在普通方法添加synchrodized关键字
 */
class SumNumber implements Runnable {

    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            synchronized (this) {
                i++;
            }
        }
    }


}