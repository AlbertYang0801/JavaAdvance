package com.albert.study.eightfeatures.functionalinterface;

import com.albert.study.TestApplication;
import com.albert.study.eightfeatures.functionalinterface.po.ClassRoom;
import com.albert.study.eightfeatures.functionalinterface.po.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.function.Supplier;

/**
 * java1.8新特性函数式接口
 * Supplier接口的学习
 *
 * @author Albert
 * @date 2020/8/5 16:24
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SupplierStudy {

    @Test
    public void testCreateStudent() {
        //创建Supplier容器，声明为Student对象，此时不会创建对象，即不会调用对象的构造方法
        Supplier<Student> studentSupplier = Student::new;
        //调用Supplier的get()方法，才会创建对象，调用构造方法
        studentSupplier.get();
    }

    /**
     * 测试Supplier的get()方法，返回的是新对象
     */
    @Test
    public void testCreateStudents() {
        Supplier<Student> studentSupplier = Student::new;
        Student a = studentSupplier.get();
        System.out.println(a);
        Student b = studentSupplier.get();
        System.out.println(b);
        //每次调用Supplier的get()方法，都创建一个新的对象，两次创建的对象地址值不同
        System.out.println(a == b);
    }

    /**
     * 测试lambda实现Supplier的get()方法
     */
    @Test
    public void testLambdaSupplier() {
        //返回字符串
        Supplier<String> stringSupplier = () -> "这是一个字符串";
        String str = stringSupplier.get();
        System.out.println(str);

        //返回自定义对象
        Supplier<Student> studentSupplier = () -> {
            return Student.builder().name("测试").age(20).build();
        };
        Student student = studentSupplier.get();
        System.out.println(student.toString());
    }

    /**
     * 测试实现Supplier的get()方法，传入自定义接口
     */
    @Test
    public void testlambdaInterface() {
        //实现自定义接口
        Supplier<ClassRoom> classRoomSupplier = () -> {
            ClassRoom classRoom = new ClassRoom() {
                @Override
                public void come(Student student) {
                    System.out.println(student.getName());
                }
            };
            return classRoom;
        };
        ClassRoom classRoom = classRoomSupplier.get();
        classRoom.come(Student.builder().name("测试接口").age(20).build());

        //lambda表达式等价实现自定义接口
        Supplier<ClassRoom> lambdaClassRoom = ()->student -> System.out.println(student.getName());
        lambdaClassRoom.get().come(Student.builder().name("测试接口lambda").build());
    }


}
