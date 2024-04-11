package com.albert.mysql.mapper;

import com.albert.mysql.TestApplication;
import com.albert.mysql.tran.TranUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class TranUserServiceTest {

    @Resource
    TranUserService tranUserService;

    @Test
    public void testUser() {
        tranUserService.insertUserA();
    }


}
