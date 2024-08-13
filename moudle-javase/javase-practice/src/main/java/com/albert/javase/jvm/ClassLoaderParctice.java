package com.albert.javase.jvm;

import org.junit.Test;
import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.io.IOException;
import java.net.URL;

/**
 * 类加载器的练习
 * @author Albert
 * @date 2021/2/25 上午9:41
 */
public class ClassLoaderParctice {

    @Test
    public void classLoader(){
        //AppClassLoader
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        //ExtClassLoader
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);
        //BoostrapClassLoader
        ClassLoader boostrapClassLoader = extClassLoader.getParent();
        //null 因为BoostrapClassLoader是用C/C++实现的，Java无法调用其实例
        System.out.println(boostrapClassLoader);
    }

    @Test
    public void boostrapClassLoaderPath(){
        String property = System.getProperty("sun.boot.class.path");
        String[] split = property.split(":");
        for (String path : split) {
            System.out.println(path);
        }
    }

    @Test
    public void extClassLoaderPath(){
        String property = System.getProperty("java.ext.dirs");
        String[] split = property.split(":");
        for (String path : split) {
            System.out.println(path);
        }
    }

    @Test
    public void appClassLoaderPath(){
        String property = System.getProperty("java.class.path");
        String[] split = property.split(":");
        for (String path : split) {
            System.out.println(path);
        }
    }


    @Test
    public void create() throws IOException {
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.getContent().toString());
        }



    }

}

//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/resources.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/rt.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/sunrsasign.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jsse.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jce.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/charsets.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/jfr.jar
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/classes


//        /Users/yangjunwei/Library/Java/Extensions
//        /Library/Java/JavaVirtualMachines/jdk1.8.0_251.jdk/Contents/Home/jre/lib/ext
//        /Library/Java/Extensions
//        /Network/Library/Java/Extensions
//        /System/Library/Java/Extensions
//        /usr/lib/java