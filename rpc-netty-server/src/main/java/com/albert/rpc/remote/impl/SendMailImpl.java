package com.albert.rpc.remote.impl;

import com.albert.rpc.remote.SendMail;
import com.albert.rpc.remote.vo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@Service
@Slf4j
public class SendMailImpl implements SendMail {
    @Override
    public boolean sendMail(UserInfo userInfo) {
        log.info("发送邮件成功:{}:{}", userInfo.getName(), userInfo.getPhone());
        return true;
    }
}
