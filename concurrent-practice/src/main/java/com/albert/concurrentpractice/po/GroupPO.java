package com.albert.concurrentpractice.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Albert
 * @date 2020/7/31 18:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupPO {

    private String groupId;
    private String groupName;
    private List<UserPO> users;
    private Map<String,UserPO> userMap;

}
