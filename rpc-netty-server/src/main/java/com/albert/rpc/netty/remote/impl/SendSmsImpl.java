package com.albert.rpc.netty.remote.impl;

import com.albert.rpc.netty.remote.SendSms;
import com.albert.rpc.netty.remote.vo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@Service
public class SendSmsImpl implements SendSms {
    @Override
    public boolean sendMsg(UserInfo userInfo) {
        return false;
    }
}
