package com.albert.concurrent.threadlocal.test;

import lombok.Data;

import java.util.UUID;

/**
 * @author yangjunwei
 * @date 2024/7/3
 */
@Data
public class UserVo {

    private String name;
    private String age;
    private String address;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String address6;
    private String address7;
    private String address8;

    public UserVo buildTest() {
        this.name = UUID.randomUUID().toString();
        this.age = UUID.randomUUID().toString();
        this.address = UUID.randomUUID().toString();
        this.address1 = UUID.randomUUID().toString();
        this.address2 = UUID.randomUUID().toString();
        this.address3 = UUID.randomUUID().toString();
        this.address4 = UUID.randomUUID().toString();
        this.address5 = UUID.randomUUID().toString();
        this.address6 = UUID.randomUUID().toString();
        this.address7 = UUID.randomUUID().toString();
        this.address8 = UUID.randomUUID().toString();
        return this;
    }

    public static UserVo getTestData() {
        UserVo userVo = new UserVo();
        userVo.buildTest();
        return userVo;
    }


}
