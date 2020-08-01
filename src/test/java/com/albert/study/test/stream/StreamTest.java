package com.albert.study.test.stream;

import com.albert.study.TestApplication;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Albert
 * @date 2020/7/17 11:08
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class StreamTest {

    /**
     * 测试stream的joining()方法
     * list根据指定字符串连接
     */
    @Test
    public void testJoining() {
        List<String> list = Lists.newArrayList();
        list.add("1000010007");
        list.add("1000010007");
        list.add("1000010007");
        String collect = list.stream().collect(Collectors.joining(":"));
        log.info("collect result : {}", collect);
    }






}
