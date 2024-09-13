package com.albert.spring.controller;

import lombok.Data;

/**
 * @author yangjunwei
 * @date 2024/8/26
 */
@Data
public class UserSortVO {

    private Integer id;

    private String name;

    private String sex;

    private String sortType = "DESC";

}
