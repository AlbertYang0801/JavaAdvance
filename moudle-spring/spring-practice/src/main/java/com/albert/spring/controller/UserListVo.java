package com.albert.spring.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/8/26
 */
@Data
public class UserListVo {

    private List<UserVo> node1;

    @JsonIgnore
    private List<UserVo> node2;


}
