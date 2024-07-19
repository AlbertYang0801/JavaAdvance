package com.albert.spring.autowired.configuration;

/**
 * @author yangjunwei
 * @date 2024/7/17
 */
//@Component
public class Huawei {

    Car car;

    public Huawei(Car car) {
        this.car = car;
    }


}
