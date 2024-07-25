package com.albert.rpc.remote.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 远程交互的实体类，必须序列化
 * @author yangjunwei
 * @date 2024-06-11
 */
@Data
public class UserInfo implements Serializable {

    private String name;
    private String phone;


}
