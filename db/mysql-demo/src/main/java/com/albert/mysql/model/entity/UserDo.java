package com.albert.mysql.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yjw
 * @date 2024/4/12 0:40
 */
@Data
@TableName(value = "user")
public class UserDo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer age;

    public UserDo build(String username) {
        this.username = username;
        this.age = 1;
        return this;
    }


}
