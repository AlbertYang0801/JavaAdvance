package com.albert.javase.guava.bean;

import lombok.Data;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2023/2/21 10:51 上午
 */
@Data
public class TestVo {

    private String test;

    private List<UserVo> list;


}
