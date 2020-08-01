package com.albert.study.utils.jackson.common;

import com.albert.study.utils.jackson.po.GroupPO;
import com.albert.study.utils.jackson.po.UserPO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 创建对象公共类，方便Jackson练习
 * @author Albert
 * @date 2020/8/1 11:03
 */
public class ObjectCommon {

    /**
     * 生成一个UserPO对象
     * @param name
     * @return
     */
    public static UserPO getUserPO(String name) {
        //获取当前时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = LocalDateTime.now().format(dateTimeFormatter);
        UserPO userPO = UserPO.builder()
                .userId(UUID.randomUUID().toString())
                .name(name)
                .time(now)
                .build();
        return userPO;
    }

    /**
     * 生成一个包含list的GroupPO对象
     * @param groupName
     * @param userPOList
     * @return
     */
    public static GroupPO getGroupPO(String groupName, List<UserPO> userPOList) {
        return GroupPO.builder()
                .groupId(UUID.randomUUID().toString())
                .groupName(groupName)
                .users(userPOList)
                .build();
    }

    /**
     * 生成一个包含Map的GroupPO对象
     * @param groupName
     * @param userPOList
     * @return
     */
    public static GroupPO getGroupPOMap(String groupName, List<UserPO> userPOList) {
        //根据用户list生成map
        Map<String, UserPO> userPOMap = userPOList.stream().collect(Collectors.toMap(UserPO::getUserId, Function.identity()));
        return GroupPO.builder()
                .groupId(UUID.randomUUID().toString())
                .groupName(groupName)
                .users(userPOList)
                .userMap(userPOMap)
                .build();
    }
    
    
    
}
