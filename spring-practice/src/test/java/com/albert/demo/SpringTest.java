package com.albert.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Albert
 * @date 2020/8/4 09:52
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SpringTest {

    @Test
    public void testLocalDateTime() {
        List<Integer> list = new ArrayList<>();
        System.out.println(CollectionUtil.isEmpty(list));
        System.out.println(CollUtil.isEmpty(list));
        System.out.println(CollUtil.isNotEmpty(list));

    }


}



