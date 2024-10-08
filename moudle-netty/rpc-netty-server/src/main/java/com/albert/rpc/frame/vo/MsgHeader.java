package com.albert.rpc.frame.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求头
 * @author yjw
 */
@Data
public final class MsgHeader {

    private int crcCode = 0xabef0101;

    /**
     * 消息的ID，因为是同步处理模式，不考虑应答消息需要填入请求消息ID
     */
    private long sessionID;

    /**
     * 消息类型
     */
    private byte type;

    /**
     * 消息优先级
     */
    private byte priority;

    /**
     * 消息头额外附件
     */
    private Map<String, Object> attachment = new HashMap<String, Object>();



}
