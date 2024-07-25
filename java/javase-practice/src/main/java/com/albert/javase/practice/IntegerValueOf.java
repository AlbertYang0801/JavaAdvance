package com.albert.javase.practice;

/**
 * 测试Integer的valueOf()方法。
 * Integer的valueOf()方法是一个工厂方法，用来生成具体对象。
 * 源码：
 * Integer.valueOf()方法，为了减少类的创建，提供了一个静态类的静态代码块，将-128~127之间的包装类型值添加到了缓存中。
 * （1）判断int类型值是否在-128～127之间；
 * （2）若在，则返回缓存中对应的对象
 * （3）若不在，则新建一个Integer类型对象
 *
 * @author Albert
 * @date 2021/1/9 下午11:20
 */
public class IntegerValueOf {

    public static void main(String[] args) {
        Integer one = Integer.valueOf(-128);
        Integer two = Integer.valueOf(-128);
        System.out.println("在-128到127之间的数字比较是否相等结果为：" + (one == two));


        Integer three = Integer.valueOf(128);
        Integer four = Integer.valueOf(128);
        System.out.println("不在-128到127之间的数字比较是否相等结果为：" + (three == four));
    }


}
