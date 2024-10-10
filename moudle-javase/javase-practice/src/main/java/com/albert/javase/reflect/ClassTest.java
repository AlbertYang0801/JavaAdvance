package com.albert.javase.reflect;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author yangjunwei
 * @date 2024/8/15
 */
public class ClassTest {

    /**
     * 测试获取当前类实现的所有接口
     * @throws ClassNotFoundException
     */
    @Test
    public void testGenericInterfaces() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.util.HashMap");
        //获取class实现的接口
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        for (int i = 0; i < genericInterfaces.length; i++) {
            System.out.println(genericInterfaces[i].getTypeName());
        }
    }

    @Test
    public void testGetActualTypeArguments() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("java.util.HashMap");
        //获取class实现的接口
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            //Map<K,V> 这种接口为带类型参数的接口
            ParameterizedType anInterface = (ParameterizedType) genericInterface;
            System.out.println(anInterface);

            //获取接口的类型参数
            Type[] actualTypeArguments = anInterface.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
            System.out.println("--------");
        }
    }




}
