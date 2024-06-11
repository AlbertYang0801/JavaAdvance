package com.albert.rpc.netty.remote.service;

import cn.hutool.system.UserInfo;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
public interface SendSms {

    boolean sendMsg(UserInfo userInfo);

}
