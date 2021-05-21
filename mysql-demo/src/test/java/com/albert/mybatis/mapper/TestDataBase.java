package com.albert.mybatis.mapper;

import com.albert.mybatis.TestApplication;
import com.albert.mybatis.model.po.TestData;
import com.albert.mybatis.model.po.UserInfoPO;
import com.albert.utils.jackson.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestDataBase {

    @Resource
    UserInfoMapper userMapper;

    @Resource
    TestMapper testMapper;

    @Test
    public void testUser() {
        List<UserInfoPO> userInfoList = userMapper.getUserInfoList();
        System.out.println(userInfoList);
    }


}
