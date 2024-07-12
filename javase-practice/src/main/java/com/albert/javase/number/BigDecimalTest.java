package com.albert.javase.number;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author yangjunwei
 * @date 2024/7/5
 */
public class BigDecimalTest {

    @Test
    public void compareTest(){
        BigDecimal one = new BigDecimal(1);
        BigDecimal two = new BigDecimal(2);
        System.out.println(one.compareTo(two));
    }


}
