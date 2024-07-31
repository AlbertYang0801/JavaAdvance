package com.albert.javase.test.service.impl;

import com.albert.javase.test.service.ITestService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author yjw
 * @date 2024/7/31 22:19
 */
@Service
public class TestServiceImpl implements ITestService {

    @Override
    public int buy(String name) {
        int order = createOrder(name);
        return order+1;
    }

    @SneakyThrows
    @Override
    public int createOrder(String name) {
        Thread.sleep(2000);
        return 0;
    }

}
