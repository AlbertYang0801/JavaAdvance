package com.albert.study.test.number;

import com.albert.study.TestApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Albert
 * @date 2020/8/1 18:22
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class NumberTest {

    @Test
    public void testDoubleToInt(){
        Double num = 2.99;
        //强转，只取整数位
        int i = num.intValue();
        log.info("double to int : {}",i );


    }



}
