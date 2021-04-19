package com.albert.mybatis.service;

import com.albert.mybatis.mapper.TestMapper;
import com.albert.mybatis.model.po.TestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Albert
 * @date 2021/4/19 下午4:03
 */
@Service
@Slf4j
public class TestService {

    @Resource
    TestMapper testMapper;

    public TestData test(){
        TestData data = testMapper.getData();
        return data;
    }


}
