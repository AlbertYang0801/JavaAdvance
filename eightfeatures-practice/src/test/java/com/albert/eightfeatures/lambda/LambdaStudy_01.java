package com.albert.eightfeatures.lambda;

import com.albert.eightfeatures.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * java8新特性
 * Lambda语法格式练习
 * @author Albert
 * @date 2020/8/4 09:52
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LambdaStudy_01 {

    /**
     * 函数式接口，只包含一个抽象方法
     */
    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {

        LambdaStudy_01 lambdaTest01 = new LambdaStudy_01();
        //声明参数类型
        MathOperation addition = (int a, int b) -> (a + b);
        //不声明参数类型
        MathOperation subtraction = (a, b) -> a - b;
        //大括号里的返回语句
        MathOperation multiplication = (a, b) -> {
            return a * b;
        };
        //没有大括号及返回语句
        MathOperation division = (a,b)-> a/b;


        int add = lambdaTest01.operate(10, 5, addition);
        System.out.println("10+5=" + add);
        int subtrac = lambdaTest01.operate(10, 5, subtraction);
        System.out.println("10-5=" + subtrac);
        int multip = lambdaTest01.operate(10, 5, multiplication);
        System.out.println("10*5=" + multip);
        int divice = lambdaTest01.operate(10, 5, division);
        System.out.println("10/5=" + divice);


        //没有括号
        GreetingService msg = message -> System.out.println("Hello "+message);
        msg.sayMessage("yh");
        //有括号
        GreetingService greetingService = (message)->{
            System.out.println("Hi "+message);
        };
        greetingService.sayMessage("胖子");

    }

    @Test
    public void testInnerClass(){
        //接口的匿名类写法
        MathOperation innerClass = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };
        System.out.println("匿名内部类实现接口：  "+innerClass.operation(1,2));

        //lambda表达式写法
        MathOperation lambda = (a,b)->a+b;
        System.out.println("lambda表达式实现接口：  "+lambda.operation(1,2));

    }



}


/**
 * lambda允许把一个函数作为方法的参数
 * 依赖于函数式接口，接口对应注解为@FunctionalInterface
 * 函数式接口：
 *      允许有且只有一个抽象方法
 *      允许0或多个默认方法
 *      允许0或多个静态方法
 */
