package com.albert.rpc.netty.remote;

import com.albert.rpc.netty.frame.register.MyRpcServerInterface;
import com.albert.rpc.netty.remote.vo.UserInfo;

/**
 * 业务类
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@MyRpcServerInterface(className = "SendSms")
public interface SendSms {

    boolean sendMsg(UserInfo userInfo);

}
