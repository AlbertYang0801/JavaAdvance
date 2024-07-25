package com.albert.rpc.serializable.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 测试java序列化和ByteBuffer长度对比
 * @author yjw
 * @date 2024/6/7 16:59
 */
public class TestLength {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserID(100).buildUserName("Welcome to Netty");

        //将对象写到字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(userInfo);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + b.length);
        bos.close();

        System.out.println("The byte array serializable length is : " + userInfo.codeC().length);
    }



}
