package com.albert.concurrentpractice.expand.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Albert
 * @date 2020/8/2 21:34
 */
public class ParkingCars {
    private static final int NUMBER_OF_CARS = 30;
    private static final int NUMBER_OF_PARKING_SLOT = 10;

    public static void main(String[] args) {
        Semaphore parkingSlot = new Semaphore(NUMBER_OF_PARKING_SLOT, true);    // "车位",采用FIFO,设置true。
        ExecutorService service = Executors.newCachedThreadPool();    // 创建线程池。模拟30辆车"停车"。
        for (int carNo = 1; carNo <= NUMBER_OF_CARS; carNo++) {
            service.execute(new Car(parkingSlot, carNo));
        }
        sleep(3000);
        service.shutdown(); // 关闭线程池。 // 输出剩余可以用的资源数。
        System.out.println(parkingSlot.availablePermits() + " 个停车位可以用!");
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
