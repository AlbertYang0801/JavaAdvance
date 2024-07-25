package com.albert.javase.jvm;

/**
 * 自定义ClassLoader
 * @author Albert
 * @date 2021/2/26 下午3:46
 */
public class CustomClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(null,null,0,0);
    }
}
