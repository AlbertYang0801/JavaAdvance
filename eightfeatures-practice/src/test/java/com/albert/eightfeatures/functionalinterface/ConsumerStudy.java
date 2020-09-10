package com.albert.eightfeatures.functionalinterface;

import com.albert.eightfeatures.TestApplication;
import com.albert.eightfeatures.functionalinterface.po.ClassRoom;
import com.albert.eightfeatures.functionalinterface.po.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Consumer;

/**
 * java1.8新特性函数式接口
 * Consumer接口的学习
 * Consumer接口定义:接收一个入参并且不返回结果的操作。
 *
 * @author Albert
 * @date 2020/8/5 16:24
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ConsumerStudy {

    /**
     * 测试Consumer接口传入对象为字符串
     */
    @Test
    public void testConsumer() {
        Consumer<String> stringConsumer = name -> System.out.println(name);
        stringConsumer.accept("胖子");
    }

    /**
     * 测试Consumer接口传入对象为实体类
     */
    @Test
    public void testConsumerStudent() {
        Consumer<Student> studentConsumer = student -> {
            student.setName("佩奇");
            student.setAge(20);
        };
        Student student = Student.builder().build();
        studentConsumer.accept(student);
        System.out.println(student);
    }

    /**
     * 测试Consumer接口传入对象为自定义接口
     */
    @Test
    public void testConsumerInterface() {
        Consumer<ClassRoom> classRoomConsumer = classRoom -> {
            //传入的接口
            classRoom.come(Student.builder().name("测试接口").age(10).build());
        };
        //传入接口对应的实现方法
        classRoomConsumer.accept(this::prinf);
    }

    private void prinf(Student student) {
        System.out.println(student);
    }

    /**
     * 测试Consumer接口的andThen()方法
     * andThen()方法，消费数据的时候，首先进行一个Consumer操作，然后再做一个Consumer操作。
     */
    @Test
    public void testAndThen() {
        Consumer<String> stringConsumer = str -> System.out.println(str.toLowerCase());
        Consumer<String> studentConsumer = str -> System.out.println(str.toUpperCase());
        stringConsumer.andThen(studentConsumer).accept("Hello world");
    }

    /**
     * 练习Consumer接口的andThen()方法
     * 将array里的数据按照顺序打印
     */
    @Test
    public void testArray() {
        String[] array = {"大雄，男", "静香，女", "胖虎，男"};
        Consumer<String> nameConsumer = str -> {
            System.out.println("姓名:" + str.split("，")[0]);
        };
        Consumer<String> sexConsumer = str -> {
            System.out.println("性别:" +  str.split("，")[1]);
        };
        for (String str : array) {
            nameConsumer.andThen(sexConsumer).accept(str);
        }
    }


}
