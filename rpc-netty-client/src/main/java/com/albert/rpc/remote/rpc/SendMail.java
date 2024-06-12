package com.albert.rpc.remote.rpc;

import com.albert.rpc.frame.rpcclient.MyRpcClient;
import com.albert.rpc.remote.vo.UserInfo;

/**
 * 业务类
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@MyRpcClient("sendMail")
public interface SendMail {

    boolean sendMail(UserInfo userInfo);

}
