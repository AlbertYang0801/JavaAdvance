## lambda的学习

#### 1. lambda的简述

    lambda表达式是java1.8的新特性，取代了大部分的匿名内部类，主要用来实现函数式接口，能够优化代码。
    函数式接口是只包含一个抽象方法的接口，可使用@FunctionalInterface注解标注。
    
#### 2. lambda的两种形式

* 形式1：(parameters) -> expression

* 形式2：(parameters) -> { statements; }
 
* lambda语法格式的练习见/albert-study/src/test/java/com/albert/study/eightfeatures/lambda/LambdaStudy_01.java
 
#### 3. lambda表达式的变量作用域

* lambda是闭包的，lambda可以使用的局部变量都必须是不可修改的变量

* lambda的变量作用域的练习见/albert-study/src/test/java/com/albert/study/eightfeatures/lambda/LambdaStudy_02.java






