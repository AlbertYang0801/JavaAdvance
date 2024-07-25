package com.albert.mysql.mapper;

import com.albert.mysql.TestApplication;
import com.albert.mysql.model.po.UserInfoPO;
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
public class TestDataBase {

    @Resource
    UserInfoMapper userMapper;

    @Test
    public void testUser() {
        List<UserInfoPO> userInfoList = userMapper.getUserInfoList();
        System.out.println(userInfoList);
    }


}
