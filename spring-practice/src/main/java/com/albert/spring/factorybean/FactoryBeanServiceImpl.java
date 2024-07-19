package com.albert.spring.factorybean;

import java.math.BigDecimal;

/**
 * 测试FactoryBean对象
 * FactoryBeanServiceImpl并没有主动注入到容器中
 * @author Albert
 * @date 2021/3/10 下午11:16
 */
public class FactoryBeanServiceImpl {

    public void print() {
        System.out.println("bean init!");
    }

    public static Double division(Double num1, Double num2) {
        if (num1 == null || num2 == null || num2 == 0) {
            return 0.00;
        } else {
            return num1 / num2;
        }
    }
    public static double decimal(final double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return 0d;
        } else {
            return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public static void main(String[] args) {
        Double division = division(15.3333666666d, 1024d);
        System.out.println(division);
        double v = decimal(division) * 100;
        System.out.println(v);
    }

}
