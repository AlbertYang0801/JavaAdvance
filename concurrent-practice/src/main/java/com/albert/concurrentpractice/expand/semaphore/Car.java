package com.albert.concurrentpractice.expand.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Albert
 * @date 2020/8/2 21:33
 */
public class Car implements Runnable {

    private final Semaphore parkingSlot;
    private int carNo;

    public Car(Semaphore parkingSlot, int carNo) {
        this.parkingSlot = parkingSlot;
        this.carNo = carNo;
    }

    @Override
    public void run() {
        try {
            parkingSlot.acquire(); // 车尝试获取"车位"
            parking();
            sleep(300);
            leaving();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parkingSlot.release(); // 释放"车位"
        }
    }

    private void parking() {
        System.out.println(String.format("%d号车泊车", carNo));
    }

    private void leaving() {
        System.out.println(String.format("%d号车离开车位", carNo));
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
