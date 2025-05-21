package com.albert.message.db.service;

import com.albert.message.db.entiry.MessagesDo;
import com.albert.message.db.mapper.MessagesMapper;
import com.google.protobuf.Message;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-12
 */
public class MessagesTemplate {

    private MessagesMapper messagesMapper;

    public MessagesTemplate(MessagesMapper messagesMapper) {
        this.messagesMapper = messagesMapper;
    }

    public void save(MessagesDo messagesDo) {
        messagesMapper.insertMessage(messagesDo);
    }

    public List<MessagesDo> queryPendingMessages(String topic, Long time) {
        return messagesMapper.queryPendingMessages(topic, time);
    }

    public boolean updateMessageStatus(Long id, String status) {
        return messagesMapper.updateMessageStatus(id, status);
    }

    public boolean updateMessageStatuss(List<MessagesDo> messagesDos) {
        messagesDos.forEach(item -> updateMessageStatus(item.getId(), item.getStatus()));
        return true;
    }

    public boolean lockPendingMessage(Long id) {
        MessagesDo messagesDO = messagesMapper.lockPendingMessage(id);
        return messagesDO != null;
    }

    public void incrRetryCountByIds(List<Long> retryMessageIds){
        messagesMapper.incrRetryCountByIds(retryMessageIds);
    }

}
