package com.albert.eightfeatures.functionalinterface.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Albert
 * @date 2020/8/5 16:58
 */
@Data
@AllArgsConstructor
@Builder
public class Student {

    String name;
    int age;

    //构造方法，创建对象时会调用
    public Student() {
        System.out.println("我被初始化了");
    }


}

