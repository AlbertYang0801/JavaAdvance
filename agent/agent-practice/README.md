# APM应用探针

## JavaAgent

### 静态注入

```java
-javaagent:/agent-practice/target/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

### 动态注入-attach

与JVM进行通信，动态加载javaAgent。
支持在Java应用运行时注入agent。

```java
 public static void main(String[] args) throws Exception {
        String pid = "66820";
        VirtualMachine vm = null;
        try {
            //1.根据pid，与目标jvm进程建立Socket连接
            vm = VirtualMachine.attach(pid);
            vm.loadAgent("/target/agent-practice-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
        } finally {
            //2.程序结束时，卸载agent
            if (vm != null) {
                vm.detach();
            }
        }
        Thread.sleep(20000);
    }
```




## Aspect

Aspect作为 AOP的一种实现，可以拦截普通Java类。

SpringAOP 只能拦截Spring管理的类。


利用ByteBuddy进行类增强，设置匹配的类和方法，然后调用对应的Before方法。


1. 埋点
2. 类改造



