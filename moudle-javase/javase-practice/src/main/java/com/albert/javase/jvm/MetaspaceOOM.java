//package com.albert.javase.jvm;
//
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.Opcodes;
//
///**
// * 元空间内存溢出
// * -XX:MaxMetaspaceSize=8m
// * @author yjw
// * @date 2022/4/4 11:16
// */
//public class MetaspaceOOM extends ClassLoader{
//
//    public static void main(String[] args) {
//        int j = 0;
//        try {
//
//            MetaspaceOOM data = new MetaspaceOOM();
//            for (int i = 0; i < 10000; i++, j++) {
//                ClassWriter classWriter = new ClassWriter(0);
//                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
//                byte[] code = classWriter.toByteArray();
//                data.defineClass("Class" + i, code, 0, code.length);
//            }
//        }finally {
//            System.out.println(j);
//        }
//    }
//
//
//}
