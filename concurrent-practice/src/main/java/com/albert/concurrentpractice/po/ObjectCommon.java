package com.albert.concurrentpractice.po;


import com.albert.concurrentpractice.po.GroupPO;
import com.albert.concurrentpractice.po.UserPO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 创建对象公共类，方便Jackson练习
 *
 * @author Albert
 * @date 2020/8/1 11:03
 */
public class ObjectCommon {

    /**
     * 生成一个UserPO对象
     */
    public static UserPO getUserPO(String name) {
        //获取当前时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String now = LocalDateTime.now().format(dateTimeFormatter);
        return UserPO.builder()
                .userId(UUID.randomUUID().toString())
                .name(name)
                .time(now)
                .build();
    }

    /**
     * 生成一个包含list的GroupPO对象
     */
    public static GroupPO getGroupPO(String groupName, List<UserPO> userList) {
        return GroupPO.builder()
                .groupId(UUID.randomUUID().toString())
                .groupName(groupName)
                .users(userList)
                .build();
    }

    /**
     * 生成一个包含Map的GroupPO对象
     */
    public static GroupPO getGroupPOMap(String groupName, List<UserPO> userList) {
        //根据用户list生成map
        Map<String, UserPO> userMap = userList.stream().collect(Collectors.toMap(UserPO::getUserId, Function.identity()));
        return GroupPO.builder()
                .groupId(UUID.randomUUID().toString())
                .groupName(groupName)
                .users(userList)
                .userMap(userMap)
                .build();
    }


}
