package com.albert.rpc.remote.controller;

import com.albert.rpc.remote.rpc.SendMail;
import com.albert.rpc.remote.rpc.SendSms;
import com.albert.rpc.remote.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjw
 * @date 2024/5/29 23:02
 */
@RestController
public class TestController {

    @Autowired
    SendSms sendSms;
    @Autowired
    SendMail sendMail;

    @GetMapping("/sms")
    public boolean sendSms() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("小红");
        userInfo.setPhone("166");
        return sendSms.sendMsg(userInfo);
    }

    @GetMapping("/mail")
    public boolean sendMail() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("小蓝");
        userInfo.setPhone("199");
        return sendMail.sendMail(userInfo);
    }


}
