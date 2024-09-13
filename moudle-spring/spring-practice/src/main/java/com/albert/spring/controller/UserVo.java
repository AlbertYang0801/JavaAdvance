package com.albert.spring.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author yangjunwei
 * @date 2024/8/26
 */
@Data
public class UserVo {

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private Integer id;

    @JsonIgnore
    private String name;

    private String sex;


}
