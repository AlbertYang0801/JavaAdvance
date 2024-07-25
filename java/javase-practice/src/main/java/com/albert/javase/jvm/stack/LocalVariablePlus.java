package com.albert.javase.jvm.stack;

import lombok.SneakyThrows;

/**方法局部变量的安全问题
 * @author yjw
 * @date 2022/4/3 12:08
 */
public class LocalVariablePlus {

    /**
     * 线程安全
     */
    public static void test() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("1");
        stringBuffer.append("2");
        stringBuffer.append("3");
        System.out.println(stringBuffer);
    }

    /**
     * 线程不安全
     */
    public static void test1(StringBuffer stringBuffer) {
        stringBuffer.append("1");
        stringBuffer.append("2");
        stringBuffer.append("3");
        System.out.println(stringBuffer);
    }

    /**
     * 线程不安全
     */
    public static StringBuffer test2() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("1");
        stringBuffer.append("2");
        stringBuffer.append("3");
        return stringBuffer;
    }

    @SneakyThrows
    public static void main(String[] args) {
        StringBuffer stringBuffer = test2();
        for(int i=0;i<2;i++){
            new Thread(()->{
                stringBuffer.append("o");
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(stringBuffer);
    }


}
