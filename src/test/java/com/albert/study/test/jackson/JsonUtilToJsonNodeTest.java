package com.albert.study.test.jackson;

import com.albert.study.TestApplication;
import com.albert.study.jackson.common.ObjectCommon;
import com.albert.study.jackson.po.GroupPO;
import com.albert.study.jackson.po.UserPO;
import com.albert.study.jackson.utils.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Albert
 * @date 2020/8/1 11:01
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class JsonUtilToJsonNodeTest {

    private static JsonNode jsonNode;

    @Test
    public void testMapToJsonNode() {
        //创建一个list
        List<UserPO> userPOList = Lists.newArrayList();
        userPOList.add(ObjectCommon.getUserPO("1号"));
        userPOList.add(ObjectCommon.getUserPO("2号"));
        userPOList.add(ObjectCommon.getUserPO("3号"));
        //获取map
        GroupPO group = ObjectCommon.getGroupPOMap("ok", userPOList);
        //获取包含map的jsonNode对象
        JsonNode jsonNode = JsonUtil.getJsonNode(JsonUtil.toString(group));
        log.info("map to jsonNode : {}", jsonNode.toString());
        log.info("jsonNode get map : {}", testJsonNodeGet(jsonNode));
        log.info("jsonNode change : {}", changeJsonNode(jsonNode));
        userPOList = fillJsonNodeToList(jsonNode);
        log.info("jsonNode to list,{}",userPOList);
    }

    //测试get方法和map类型相关方法
    private String testJsonNodeGet(JsonNode jsonNode) {
        //测试jsonNode的get()方法，获取map
        JsonNode userMap = jsonNode.get("userMap");
        log.info("map is array : {}", userMap.isArray());
        Map<String, Object> map = JsonUtil.objectToMap(userMap);
        log.info("map to object list : {}", JsonUtil.toString(fillMapToList(map)));
        return JsonUtil.toString(map);
    }

    private List<UserPO> fillMapToList(Map<String, Object> map) {
        //将map转换为list
        Set<String> keySet = map.keySet();
        List<UserPO> userPOList = Lists.newArrayList();
        keySet.forEach(item -> {
            UserPO userPO = JsonUtil.toBean(JsonUtil.toString(map.get(item)), UserPO.class);
            userPOList.add(userPO);
        });
        return userPOList;
    }

    private JsonNode changeJsonNode(JsonNode jsonNode) {
        jsonNode = jsonNode.get("users");
        return jsonNode;
    }

    //测试jsonNode的iterator()方法
    private List<UserPO> fillJsonNodeToList(JsonNode jsonNode) {
        if (!jsonNode.isArray()) {
            return Lists.newArrayList();
        }
        List<UserPO> userPOList = Lists.newArrayList();
        Iterator<JsonNode> iterator = jsonNode.iterator();
        while (iterator.hasNext()) {
            JsonNode next = iterator.next();
            UserPO userPO = JsonUtil.toBean(JsonUtil.toString(next), UserPO.class);
            userPOList.add(userPO);
        }
        return userPOList;
    }

    /**
     * 测试jsonNode获取对象属性方法
     */
    @Test
    public void getJsonNodeAsText(){
        UserPO userPO = ObjectCommon.getUserPO("对象属性");
        JsonNode jsonNode = JsonUtil.getJsonNode(JsonUtil.toString(userPO));
        String userId = jsonNode.get("userId").asText();
        log.info("userId is {}",userId);
    }


}
