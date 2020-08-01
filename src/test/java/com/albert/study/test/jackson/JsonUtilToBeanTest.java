package com.albert.study.test.jackson;

import com.albert.study.TestApplication;
import com.albert.study.utils.jackson.common.ObjectCommon;
import com.albert.study.utils.jackson.po.GroupPO;
import com.albert.study.utils.jackson.po.UserPO;
import com.albert.study.utils.jackson.utils.JsonUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
public class JsonUtilToBeanTest {

    /**
     * 测试json字符串转换为Bean
     */
    @Test
    public void testStringToBean() {
        String userPoString = JsonUtil.toString(ObjectCommon.getUserPO("测试Jackson的Bean转json字符串"));
        UserPO userPO = JsonUtil.toBean(userPoString, UserPO.class);
        log.info(JsonUtil.toString(userPO));
    }

    /**
     * 测试json字符串转换为包含list的bean
     */
    @Test
    public void testStringListToBean() {
        //获取包含list的Bean对应的json字符串
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("1号"));
        userPOList.add(ObjectCommon.getUserPO("2号"));
        userPOList.add(ObjectCommon.getUserPO("3号"));
        GroupPO group = ObjectCommon.getGroupPO("第一组", userPOList);
        String groupString = JsonUtil.toString(group);
        //将json字符串转换为包含list的Bean
        GroupPO groupPO = JsonUtil.toBean(groupString, GroupPO.class);
        log.info(groupPO.toString());
    }

    /**
     * 测试json字符串转换为包含map的bean
     */
    @Test
    public void testStringMapToBean() {
        //获取包含list的Bean对应的json字符串
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("1号"));
        userPOList.add(ObjectCommon.getUserPO("2号"));
        userPOList.add(ObjectCommon.getUserPO("3号"));
        GroupPO group = ObjectCommon.getGroupPOMap("第一组", userPOList);
        String groupString = JsonUtil.toString(group);
        //将json字符串转换为包含list的Bean
        GroupPO groupPO = JsonUtil.toBean(groupString, GroupPO.class);
        log.info(groupPO.toString());
    }


}
