package com.albert.spring.aop.bytebuddy;

/**
 * @author yangjunwei
 * @date 2024/7/22
 */
public class OriginBean {

    public String name;

    @Polite
    public String hello() {
        return "Hello " + name + " .";
    }

    public String morning() {
        return "Morning " + name + " .";
    }


}
