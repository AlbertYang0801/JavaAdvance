package com.albert.practice.eight.methodreference;

import com.albert.practice.TestJavaApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * java8新特性 方法引用练习
 * @author Albert
 * @date 2020/8/4 09:52
 */
@SpringBootTest(classes = TestJavaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class MethodReferenceStudy {

    @Test
    public void test(){

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        list.stream().forEach(System.out::println);
    }


}

