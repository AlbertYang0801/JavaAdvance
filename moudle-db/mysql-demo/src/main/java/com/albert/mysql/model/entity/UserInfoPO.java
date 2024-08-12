package com.albert.mysql.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Albert
 * @date 2020/10/29 21:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoPO {

    private Integer id;

    private String username;

    private Integer sex;

    private Double property;

    private Float money;

    private Date createTime;

    private Date timestamp;


}
