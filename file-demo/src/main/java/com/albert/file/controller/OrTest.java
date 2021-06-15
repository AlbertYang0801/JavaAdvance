package com.albert.file.controller;

/**
 * @author yangjunwei
 * @date 2021/6/3 下午8:38
 */
public class OrTest {

    private boolean a(){
        System.out.println("a");
        return false;
    }

    private boolean b(){
        System.out.println("b");
        return true;
    }

    public static void main(String[] args) {
        OrTest orTest = new OrTest();
        if(orTest.a()&orTest.b()){
            System.out.println(111);
        }
    }



}
