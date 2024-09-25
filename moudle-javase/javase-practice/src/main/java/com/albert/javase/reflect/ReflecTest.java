package com.albert.javase.reflect;

import lombok.SneakyThrows;

import java.lang.reflect.Proxy;

/**
 * @author yangjunwei
 * @date 2024/9/25
 */
public class ReflecTest {

    @SneakyThrows
    public static void main(String[] args) {
        IA ia = (IA) createObject(IA.class.getName() + "$getHelloName=123");
        System.out.println(ia.getHelloName());

        ia = (IA) createObject(IA.class.getName() + "$getTest=123");
        System.out.println(ia.getHelloName());
    }

    public static Object createObject(String str) throws ClassNotFoundException {
        String[] strs = str.split("\\$");
        String className = strs[0];
        String[] split = strs[1].split("=");

        String methodName = split[0];
        String param = split[1];
        Class<?> aClass = Class.forName(className);
        Class[] classes = {aClass};
        return Proxy.newProxyInstance(aClass.getClassLoader(), classes, new IaInvocationHandler(methodName, param));
    }


}


interface IA {

    String getHelloName();


}