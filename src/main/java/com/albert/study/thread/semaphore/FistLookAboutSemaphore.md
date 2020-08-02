## Semaphore 学习

#### 1. Semaphore   

        CountDownLatch 位于java.util.concurrent.CountDownLatch;
        CountDownLatch是线程相关的一个计数器。 CountDownLatch计数器的操作是原子性的，同时只有一个线程去
    操作这个计数器，所以同时只有一个线程能减少这个计数器里面的值。可以通过为CountDownLatch设置初始的数字作
    为计数，任何对象都可以调用await()方法，直到这个计数器的计数值被其他的线程减到0为止，调用await()方法的
    线程即可继续执行。
    

#### 2. Semaphore的例子

##### 2.1 停车示例。停车场只有10个车位，现在有30辆车去停车。当车位满时出来一辆车才能有一辆车进入停车。。





