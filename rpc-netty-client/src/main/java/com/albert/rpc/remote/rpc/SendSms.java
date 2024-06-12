package com.albert.rpc.remote.rpc;

import com.albert.rpc.frame.rpcclient.MyRpcClient;
import com.albert.rpc.remote.vo.UserInfo;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@MyRpcClient("sendSms")
public interface SendSms {

    boolean sendMsg(UserInfo userInfo);

}
