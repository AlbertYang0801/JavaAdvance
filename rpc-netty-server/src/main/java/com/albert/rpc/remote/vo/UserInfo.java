package com.albert.rpc.remote.vo;

import java.io.Serializable;

/**
 * 远程交互的实体类，必须序列化
 * @author yangjunwei
 * @date 2024-06-11
 */
public class UserInfo implements Serializable {

    private String name;
    private String phone;

    public UserInfo() {
    }

    public UserInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
