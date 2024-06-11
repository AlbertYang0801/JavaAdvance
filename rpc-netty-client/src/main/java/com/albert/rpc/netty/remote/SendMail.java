package com.albert.rpc.netty.remote.service;


import com.albert.rpc.netty.remote.vo.UserInfo;

/**
 * 业务类
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
public interface SendMail {

    boolean sendMail(UserInfo userInfo);

}
