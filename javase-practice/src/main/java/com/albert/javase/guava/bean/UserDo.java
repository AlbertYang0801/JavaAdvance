package com.albert.javase.guava.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author yangjunwei
 * @date 2022/4/14 2:58 下午
 */
@Data
@Builder
public class UserDo {

    private Integer id;
    private String name;

}
