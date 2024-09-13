package com.albert.db.service;

import com.albert.db.entiry.MessagesDo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-12
 */
public interface IMessagesService extends IService<MessagesDo> {

    List<MessagesDo> queryPendingMessages(String topicName, long time);


    void updateMessageStatus(List<MessagesDo> messagesDoList);


    void incrRetryCountByIds(List<Long> retryMessageIds);



}
