package com.albert.mysql.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yjw
 * @date 2024/4/12 0:40
 */
@Data
@TableName(value = "user")
public class UserDo {

    private Integer id;
    private String username;
    private Integer sex;

    public UserDo build(String username){
        this.username=username;
        this.sex=1;
        return this;
    }

}
