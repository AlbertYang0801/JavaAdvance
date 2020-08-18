package com.albert.study.eightfeatures.functionalinterface;

import com.albert.study.TestApplication;
import com.albert.study.eightfeatures.functionalinterface.po.Student;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToLongBiFunction;
import java.util.stream.Collectors;

/**
 * java1.8新特性函数式接口
 * Function接口的学习
 * Function接口:接收一个入参并产生一个结果的函数
 *
 * @author Albert
 * @date 2020/8/6 11:09
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class FunctionStudy {

    @Test
    public void testFunction() {
        //指定入参和出参类型
        Function<Student, String> studentFunction = student -> student.getName();
        //调用Function，传入参数，获取返回结果
        String str = studentFunction.apply(Student.builder().name("测试Function").build());
        System.out.println(str);
    }

    @Test
    public void testFunctionParseInt() {
        Function<String, Integer> function = num -> {
            //Function.identity() 代表Function对象本身
            System.out.println(Function.identity().apply("30"));
            return Integer.parseInt(num);
        };
        Integer apply = function.apply("10");
        System.out.println(apply);
    }

    /**
     * 测试Function接口的andThen()方法
     * andThen()方法，
     * 先进行第一个操作，再根据第一个操作的返回值进行第二个操作（括号里的）。
     */
    @Test
    public void testFunctionAndThen() {
        //先将字符串转换为int类型
        Function<String, Integer> oneFunction = num -> Integer.parseInt(num);
        //再进行乘的操作
        Function<Integer, Integer> twoFunction = num -> num * 20;
        Integer num = oneFunction.andThen(twoFunction).apply("20");
        System.out.println(num);
    }

    /**
     * 练习Function接口的andThen()方法
     * 输出年龄加上100
     */
    @Test
    public void testFunctionAndThenTopic() {
        String person = "佩奇,100";
        Function<String, String> oneFunction = str -> str.split(",")[1];
        Function<String, Integer> twoFunction = str -> Integer.parseInt(str);
        Function<Integer, Integer> threeFunction = num -> num + 100;
        Integer apply = oneFunction.andThen(twoFunction).andThen(threeFunction).apply(person);
        System.out.println(apply);
    }

    /**
     * 测试Function接口的compose()方法
     * compose()方法，操作数据的时候：
     * 先进行括号里的操作，再根据，括号里操作的返回值作为入参，进行第二个操作
     */
    @Test
    public void testFunctionCompose(){
        Function<String, Integer> oneFunction =str->Integer.parseInt(str);
        Function<Integer, Integer> twoFunction = num -> num*10;
        Integer apply = twoFunction.compose(oneFunction).apply("10");
        System.out.println(apply);
    }

    /**
     * 测试Function接口的identity()方法
     * 返回一个与Function接口传入参数一致的对象
     * student->student
     */
    @Test
    public void testFunctionIdentity(){
        List<Student> list = Lists.newArrayList();
        list.add(Student.builder().name("阿古朵").age(10).build());
        list.add(Student.builder().name("大熊").age(21).build());
        //将list转换为map，name作为key，student作为value
        Map<Integer, Student> collect = list.stream()
                .collect(Collectors.toMap(Student::getAge, Function.identity()));
        System.out.println(collect);
    }

    /**
     * 测试扩展的Function接口-BiFunction接口
     * 支持传入两个参数，返回一个结果
     */
    @Test
    public void testBiFunction(){
        BiFunction<Integer,Integer,String> biFunction = (a,b)->String.valueOf(a+b);
        String apply = biFunction.apply(3, 5);
        System.out.println(apply);
    }



}
