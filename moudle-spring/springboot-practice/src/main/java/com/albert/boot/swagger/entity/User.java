package com.albert.boot.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangjunwei
 * @date 2024/12/5 13:57
 */
@Data
@ApiModel("用户信息")
public class User {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("年龄")
    private String age;

}
