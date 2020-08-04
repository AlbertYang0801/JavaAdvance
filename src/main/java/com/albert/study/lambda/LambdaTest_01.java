package com.albert.study.lambda;

/**
 * Lambda简单练习
 * @author Albert
 * @date 2020/8/4 09:52
 */
public class LambdaTest_01 {

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

        LambdaTest_01 lambdaTest01 = new LambdaTest_01();
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



}
