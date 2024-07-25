package com.albert.rpc.remote.impl;

import com.albert.rpc.remote.SendSms;
import com.albert.rpc.remote.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@Service
@Slf4j
public class SendSmsImpl implements SendSms {
    @Override
    public boolean sendMsg(UserInfo userInfo) {
        log.info("发送短信成功:{}:{}", userInfo.getName(), userInfo.getPhone());
        return true;
    }
}
