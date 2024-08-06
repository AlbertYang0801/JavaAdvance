package com.albert.rpc.framework;

import com.albert.rpc.framework.protocol.dubbo.DubboProtocol;
import com.albert.rpc.framework.protocol.http.HttpProtocol;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class ProtocolFactory {

    /**
     * 协议处理器
     * @param name
     * @return
     */
    public static Protocol getProtocol(String name) {
        switch (name) {
            case "dubbo":
                return new DubboProtocol();
            case "http":
                return new HttpProtocol();
            default:
                break;
        }
        return new HttpProtocol();
    }


}
