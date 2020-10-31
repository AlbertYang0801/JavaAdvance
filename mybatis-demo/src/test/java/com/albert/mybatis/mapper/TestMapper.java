package com.albert.mybatis.mapper;

import com.albert.mybatis.TestApplication;
import com.albert.mybatis.defaultsource.mapper.DefaultUserInfoMapper;
import com.albert.mybatis.model.UserInfoPO;
import com.albert.mybatis.other.mapper.OtherUserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TestMapper {

    @Resource
    DefaultUserInfoMapper defaultUserInfoMapper;

    @Resource
    OtherUserInfoMapper otherUserInfoMapper;

    /**
     * 测试第一个数据源
     */
    @Test
    public void testDefaultDataSource(){
        List<UserInfoPO> userInfoList = defaultUserInfoMapper.getUserInfoList();
        System.out.println(userInfoList);
    }

    /**
     * 测试第二个数据源
     */
    @Test
    public void testUser(){
        List<UserInfoPO> userInfoList = otherUserInfoMapper.getUserInfoList();
        System.out.println(userInfoList);
    }


}
