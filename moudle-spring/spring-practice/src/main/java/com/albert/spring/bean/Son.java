package com.albert.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/19
 */
@Component
public class Son extends Parent{

    @Override
    public String toString() {
        return "son";
    }
}
