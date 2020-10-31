package com.albert.mybatis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Albert
 * @date 2020/10/29 21:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoPO {

    private String id;
    private String name;
    private Integer age;
    private Long timestamp;



}
