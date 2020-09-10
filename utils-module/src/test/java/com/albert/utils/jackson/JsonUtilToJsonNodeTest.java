package com.albert.utils.jackson;

import com.albert.utils.TestApplication;
import com.albert.utils.jackson.common.ObjectCommon;
import com.albert.utils.jackson.po.GroupPO;
import com.albert.utils.jackson.po.UserPO;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        log.info("jsonNode to list,{}", userPOList);
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
    public void getJsonNodeAsText() {
        UserPO userPO = ObjectCommon.getUserPO("对象属性");
        JsonNode jsonNode = JsonUtil.getJsonNode(JsonUtil.toString(userPO));
        String userId = jsonNode.get("userId").asText();
        log.info("userId is {}", userId);
    }

    /**
     * 测试jsonNode的findValue()方法
     */
    @Test
    public void testFindJsonNodeAsText() {
        UserPO userPO = ObjectCommon.getUserPO("对象属性");
        System.out.println(userPO.toString());
        JsonNode jsonNode = JsonUtil.getJsonNode(JsonUtil.toString(userPO));
        String time = jsonNode.findValue("time").asText();
        System.out.println(time);
    }

    /**
     * 使用findValue时，若节点中有多个相同字段，则返回第一个匹配到的字段值
     */
    @Test
    public void testFindJsonNodeRepeated(){
        Map<Object,Object> map = Maps.newHashMap();
        map.put("one","one");
        Map<String,String> stringMap = Maps.newHashMap();
        stringMap.put("one","1");
        map.put("map",stringMap);
        String mapStr = JsonUtil.toString(map);
        System.out.println(mapStr);

        JsonNode jsonNode = JsonUtil.getJsonNode(mapStr);
        //返回第一个匹配到的value值
        String one = jsonNode.findValue("one").asText();
        System.out.println(one);
    }

    /**
     * 测试JsonNode的方法会不会报异常
     */
    @Test
    public void testGetJsonNode() {
        Map<String, String> map = Maps.newHashMap();
        map.put("name", "测试");
        map.put("age", "10");
        map.put("sex", "男");
        String s = JsonUtil.toString(map);
        JsonNode jsonNode = JsonUtil.getJsonNode(s);
        JsonNode a = jsonNode.get("a");
        System.out.println(a == null ? "1" : a.textValue());
        //属性不存在，会报空指针异常
        System.out.println(a.isNull());
        System.out.println(a.isEmpty());
        System.out.println(a.textValue());
    }


}
