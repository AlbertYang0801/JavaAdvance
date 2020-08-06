package com.albert.study.eightfeatures.functionalinterface;

import com.albert.study.TestApplication;
import com.albert.study.eightfeatures.functionalinterface.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * java1.8新特性函数式接口
 * 扩展:
 * BiFunction接口，是Function接口的扩展，允许传入两个参数，返回一个结果
 *
 * @author Albert
 * @date 2020/8/6 17:56
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class BiFunctionStudy {

    @Test
    public void testBiFunction() {
        BiFunction<String, Integer, Student> studentBiFunction =
                (name, age) -> Student.builder().name(name).age(age).build();
        Student student = studentBiFunction.apply("胖子", 160);
        System.out.println(student);
    }

    /**
     * 测试BiFunction的andThen()方法
     * 把BiFunction接口第一次操作的结果当作参数传入Function接口中进行第二次操作
     */
    @Test
    public void testBiFunctionAndThen() {
        BiFunction<String, String,String> nameBiFunction =
                (name, add) ->name+add;
        //BiFunction的andThen()方法允许传入Function对象
        Function<String,String> addFunction = name-> name+"YOU";
        String apply = nameBiFunction.andThen(addFunction).apply("I", " LOVE ");
        System.out.println(apply);
    }


}
