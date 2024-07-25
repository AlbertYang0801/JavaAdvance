package com.albert.springtask.dynamic;

import org.springframework.stereotype.Component;

/**
 * @author Albert
 * @date 2020/10/21 19:50
 */
@Component
public class CommonHandler {

    public static void print() {
        System.out.println("外部静态方法");
    }


}
