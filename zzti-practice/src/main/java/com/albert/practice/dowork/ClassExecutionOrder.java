package com.albert.practice.dowork;

/**
 * 类的执行顺序
 * 父类静态代码块>父类静态变量>子类静态代码块>子类静态变量
 *              >父类普通代码块>父类构造方法>子类普通代码块>子类构造方法
 * @author Albert
 * @date 2020/11/25 上午11:41
 */
public class ClassExecutionOrder {

    public static void main(String[] args) {
        new Children();
    }

}

class Parent{

    private static String name = "parent";

    static {
        System.out.println("父类静态代码块执行");
    }

    {
        System.out.println("父类普通代码块执行");
    }

    public Parent(){
        System.out.println("父类构造方法执行");
    }


}


class Children extends Parent{

    private static String name = "children";

    static{
        System.out.println("子类静态代码块执行");
    }

    {
        System.out.println("子类普通代码块执行");
    }

    public Children(){
        System.out.println("子类构造方法执行");
    }


}
