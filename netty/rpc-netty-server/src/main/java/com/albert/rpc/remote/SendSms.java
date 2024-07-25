package com.albert.rpc.remote;

import com.albert.rpc.frame.register.MyRpcServer;
import com.albert.rpc.remote.vo.UserInfo;

/**
 * 业务类
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@MyRpcServer(className = "sendSms")
public interface SendSms {

    boolean sendMsg(UserInfo userInfo);

}
