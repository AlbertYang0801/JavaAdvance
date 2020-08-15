package com.albert.study.eightfeatures.optional;

import com.albert.study.TestApplication;
import com.albert.study.utils.jackson.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * 测试1.8的新特性Optional
 * @author Albert
 * @date 2020/8/13 19:39
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
@Slf4j
public class OptionalTest {

    @Test
    public void testOptional(){
        Optional<UserPO> userPOOptional = Optional.ofNullable(UserPO.builder().build());

        log.info(String.valueOf(userPOOptional.isPresent()));

        log.info(String.valueOf(UserPO.builder().build().getUserId()==null));

        log.info(userPOOptional.toString());

    }


}
