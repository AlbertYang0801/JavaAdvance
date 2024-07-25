package com.albert.mysql.service;

import com.albert.mysql.mapper.TestMapper;
import com.albert.mysql.model.po.TestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Albert
 * @date 2021/4/19 下午4:03
 */
@Service

public class TestService {

    @Autowired
    TestMapper testMapper;

    public TestData test(){
        TestData data = testMapper.getData();
        return data;
    }


}
