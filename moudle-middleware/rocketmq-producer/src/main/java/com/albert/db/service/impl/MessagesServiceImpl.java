package com.albert.db.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.albert.db.mapper.MessagesMapper;
import com.albert.db.entiry.MessagesDo;
import com.albert.db.service.IMessagesService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-12
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, MessagesDo> implements IMessagesService {

    @Override
    public List<MessagesDo> queryPendingMessages(String topicName, long time) {
        LambdaQueryWrapper<MessagesDo> wrapper = new LambdaQueryWrapper<MessagesDo>().eq(MessagesDo::getTopic, topicName)
                .ge(MessagesDo::getEnableConsumeTime, time);
        return this.list(wrapper);
    }

    @Override
    public void updateMessageStatus(List<MessagesDo> messagesDoList) {
        if (CollUtil.isEmpty(messagesDoList)) {
            return;
        }
        this.updateBatchById(messagesDoList);
    }

    @Override
    public void incrRetryCountByIds(List<Long> retryMessageIds) {
        if (CollUtil.isEmpty(retryMessageIds)) {
            return;
        }
        this.getBaseMapper().incrRetryCountByIds(retryMessageIds);
    }


}
