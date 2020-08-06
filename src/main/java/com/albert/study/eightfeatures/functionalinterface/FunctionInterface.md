## 函数式接口的学习

#### 1. 函数式接口的简述

    函数式接口是java1.8的新特性，函数式接口是只包含一个抽象方法的接口，可使用@FunctionalInterface注解标注。
    java1.8新特性接口增加了默认方法和静态方法，函数式接口必须只拥有一个抽象接口，允许有多个默认方法和多个静态方法。
   
   
#### 2. java1.8新增的四大函数式接口

* Function接口：允许一个入参，返回一个结果。

* Consumer接口：允许一个入参，没有返回结果。

* Supplier接口：没有入参，有返回结果，可进行延时操作。

* Predicate接口：进行判断逻辑，返回结果Boolean类型。
 
#### 3. java1.8新增的其他函数式接口

* BiFunction接口：允许两个入参，返回一个结果。

* BinaryOperator接口：继承于BiFunction接口，包含两个静态方法比较传入两个参数的大小。

#### 4. 练习

* 函数式接口的练习见： /src/test/java/com/albert/study/eightfeatures/functionalinterface




