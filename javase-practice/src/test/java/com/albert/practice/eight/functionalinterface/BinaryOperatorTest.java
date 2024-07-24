package com.albert.practice.eight.functionalinterface;

import com.albert.practice.TestJavaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * java1.8新特性函数式接口
 * 扩展：
 * BinaryOperator接口的练习：继承于BiFunction，允许传入两个参数。
 * 调用BinaryOperator接口接口的静态方法，mixby()和maxby()
 * 可用来比较传入两个参数的大小
 * @author Albert
 * @date 2020/8/6 17:23
 */
@SpringBootTest(classes = TestJavaApplication.class)
@RunWith(SpringRunner.class)
public class BinaryOperatorTest {

    /**
     * 测试BinaryOperator接口的静态方法mixby()
     */
    @Test
    public void testBinaryOperatorMinBy() {
        //比较传入类型的大小，返回小的值
        BinaryOperator<Integer> binaryOperator = BinaryOperator.minBy(Comparator.naturalOrder());
        Integer apply = binaryOperator.apply(3, 4);
        System.out.println(apply);
    }

    /**
     * 测试BinaryOperator接口的静态方法maxby()
     */
    @Test
    public void testBinaryOperatorMaxBy() {
        Function<Integer,Integer> function = num->{
          if(num==4) return 0;
          else return num;
        };
        //比较传入类型的大小，返回大的值
        BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy(Comparator.naturalOrder());
        Integer apply = binaryOperator.apply(3, 4);
        System.out.println(apply);
    }

    @Test
    public void testBinaryOperatorMaxByComparing() {
        //对传入比较器的参数进行处理
        Function<Integer,Integer> function = num->{
            if(num==4) return 0;
            else return num;
        };
        //比较传入类型的大小，返回大的值（自定义比较器）
        BinaryOperator<Integer> binaryOperator = BinaryOperator.maxBy(Comparator.comparing(function));
        Integer apply = binaryOperator.apply(3, 4);
        System.out.println(apply);
    }


}
