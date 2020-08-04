package com.albert.study.jackson;

import com.albert.study.TestApplication;
import com.albert.study.utils.jackson.common.ObjectCommon;
import com.albert.study.utils.jackson.po.GroupPO;
import com.albert.study.utils.jackson.po.UserPO;
import com.albert.study.utils.jackson.utils.JsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * JsonUtil的toString()方法练习
 * 练习Jackson的writeValueAsString()方法
 *
 * @author Albert
 * @date 2020/7/31 17:53
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class JsonUtilToStringTest {

    /**
     * 测试对象转换为json字符串
     */
    @Test
    public void testBeanToString() {
        String userPoString = JsonUtil.toString(ObjectCommon.getUserPO("测试Jackson的Bean转json字符串"));
        log.info(userPoString);
    }

    /**
     * 测试list转换为json字符串
     */
    @Test
    public void testListToString() {
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("测试Jackson的list转json字符串"));
        userPOList.add(ObjectCommon.getUserPO("测试Jackson的list转json字符串"));
        userPOList.add(ObjectCommon.getUserPO("测试Jackson的list转json字符串"));
        String userPoString = JsonUtil.toString(userPOList);
        log.info(userPoString);
    }


    /**
     * 测试map转换为json字符串
     */
    @Test
    public void testMapToString() {
        Map<Integer, UserPO> userPOMap = Maps.newHashMap();
        userPOMap.put(1, ObjectCommon.getUserPO("测试Jackson的map转json字符串"));
        userPOMap.put(2, ObjectCommon.getUserPO("测试Jackson的map转json字符串"));
        String userPoString = JsonUtil.toString(userPOMap);
        log.info(userPoString);
    }

    /**
     * 测试Bean包含list转json字符串
     */
    @Test
    public void testBeanListToString() {
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("1号"));
        userPOList.add(ObjectCommon.getUserPO("2号"));
        userPOList.add(ObjectCommon.getUserPO("3号"));
        GroupPO group = ObjectCommon.getGroupPO("第一组", userPOList);
        log.info(JsonUtil.toString(group));
    }

    /**
     * 测试Bean包含map转json字符串
     */
    @Test
    public void testBeanMapToString() {
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("1号"));
        userPOList.add(ObjectCommon.getUserPO("2号"));
        userPOList.add(ObjectCommon.getUserPO("3号"));
        GroupPO group = ObjectCommon.getGroupPOMap("第一组", userPOList);
        log.info(JsonUtil.toString(group));
    }


}
