package com.albert.study.eightfeatures.lambda;

import com.albert.study.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * java8新特性
 * Lambda的变量作用域练习
 * @author Albert
 * @date 2020/8/4 09:52
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LambdaStudy_02 {

    @FunctionalInterface
    public interface Converter {
        void convert(int i);
    }

    final static int hellonum = 3;

    //lambda表达式访问局部静态变量
    @Test
    public void testStatic() {
        //外层局部变量默认不可修改
        Converter converter = i -> System.out.println(hellonum + i);
        converter.convert(3);
    }

    //lambda表达式可以访问外层的final类型的局部变量
    @Test
    public void ConverterSum() {
        //表达式外层局部变量
        final int num = 1;
        Converter converter = i -> System.out.println(i + num);
        converter.convert(2);
    }

    //lambda表达式访问外层的局部变量可不为final修饰，但必须不能被表达式之后的代码修改（即局部变量具有隐含的final语意）
    @Test
    public void testFinal() {
        //局部变量声明可不为final，但是不能被修改，是隐含的final类型
        int num = 3;
        Converter converter = i -> System.out.println(num + i);
        converter.convert(2);
    }


}


/**
 * lambda可以使用的局部变量都必须是不可修改的变量
 */
