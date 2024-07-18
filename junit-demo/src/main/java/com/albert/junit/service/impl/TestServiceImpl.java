package com.albert.junit.service.impl;

import com.albert.junit.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024/7/18
 */
@Service
public class TestServiceImpl implements ITestService {

    @Override
    public String test() {
        return "test";
    }


}
