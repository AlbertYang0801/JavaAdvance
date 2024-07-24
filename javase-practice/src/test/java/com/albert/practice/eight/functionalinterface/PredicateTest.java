package com.albert.practice.eight.functionalinterface;

import com.albert.practice.TestJavaApplication;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * java1.8新特性函数式接口
 * Predicate接口的学习
 * 进行逻辑判断的接口，包含逻辑非、或、与，返回Boolean类型结果
 * @author Albert
 * @date 2020/8/6 16:26
 */
@SpringBootTest(classes = TestJavaApplication.class)
@RunWith(SpringRunner.class)
public class PredicateTest {

    /**
     * 测试Predicate接口的test()方法
     * 判断是非满足逻辑，返回boolean类型
     */
    @Test
    public void testPredicate(){
        Predicate<String> stringPredicate = str-> str.equals("测试");
        boolean a = stringPredicate.test("测试");
        System.out.println(a);
    }

    /**
     * 测试Predicate接口的and()方法
     * 逻辑与
     */
    @Test
    public void testPredicateAnd(){
        Predicate<String> andPredicate = str-> !Objects.isNull(str);
        Predicate<String> stringPredicate = str-> str.equals("测试");
        boolean a = andPredicate.and(stringPredicate).test("测试");
        System.out.println(a);
    }

    /**
     * 测试Predicate接口的negate()方法
     * 逻辑非
     */
    @Test
    public void testPredicateNegate(){
        Predicate<String> andPredicate = str-> Objects.isNull(str);
        boolean test = andPredicate.test("haha");
        //negate()对Predicate接口的判断结果进行取反操作
        boolean negate = andPredicate.negate().test("哈哈");
        System.out.println(test);
        System.out.println(negate);
    }

    /**
     * 测试Predicate接口的or()方法
     * 逻辑或
     */
    @Test
    public void testPredicateOr(){
        Predicate<String> onePredicate = str-> str.equals("男");
        Predicate<String> twoPredicate = str-> str.equals("女");
        boolean a = onePredicate.or(twoPredicate).test("男");
        System.out.println(a);
    }

    /**
     * 测试Predicate接口的isEqual()方法
     * Predicate接口的isEqual()可以作为一个Predicate接口对象传入
     */
    @Test
    public void testPredicateIsEqual(){
        Predicate<String> stringPredicate = str-> StringUtils.isNotBlank(str);
        boolean test = stringPredicate.and(Predicate.isEqual("测试equal")).test("测试equal");
        System.out.println(test);
    }


}
