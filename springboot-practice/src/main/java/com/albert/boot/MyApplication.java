package com.albert.boot;

import com.albert.boot.springboot.MySpringApplication;
import com.albert.boot.springboot.MySpringBootApplication;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
@MySpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        MySpringApplication.run(MyApplication.class);
    }


}
