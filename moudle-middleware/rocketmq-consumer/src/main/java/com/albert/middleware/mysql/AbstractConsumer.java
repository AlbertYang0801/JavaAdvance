package com.albert.middleware.mysql;

import com.albert.message.db.entiry.MessagesDo;
import com.albert.message.db.service.MessagesTemplate;
import com.albert.middleware.enums.MessagesStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Slf4j
public abstract class AbstractConsumer {

    @Resource
    MessagesTemplate messagesTemplate;

    public String topicName;

    /**
     * 最大重试次数
     */
    private static final int MSG_DELIVER_RETRY_TIME = 4;

    public AbstractConsumer(String topicName) {
        this.topicName = topicName;
    }

    @Transactional(rollbackFor = Exception.class)
    public void consumer() {
        //查询消息
        List<MessagesDo> messagesDOS = queryMessages();
        //处理消息
        messagesDOS.forEach(message -> {
            try {
                processMessage(message);
                message.setStatus(MessagesStatusEnum.SUCCEEDED.name());
            } catch (Exception e) {
                //重试达到最大次数
                if (message.getRetryCount() >= MSG_DELIVER_RETRY_TIME) {
                    message.setStatus(MessagesStatusEnum.FAILED.name());
                } else {
                    //不处理，继续重试
                }
                log.warn("mysql consumer error:", e);
            }
        });
        //更新消息
        updateMessages(messagesDOS);
    }

    /**
     * 消费信息
     *
     * @return
     */
    public List<MessagesDo> queryMessages() {
        //查询到期消息
        return messagesTemplate.queryPendingMessages(topicName, System.currentTimeMillis());
    }

    /**
     * 更新消息状态
     *
     * @param messagesDOS
     */
    public void updateMessages(List<MessagesDo> messagesDOS) {
        //处理成功
        List<MessagesDo> successMessages = messagesDOS.stream().filter(message -> Objects.equals(message.getStatus(), MessagesStatusEnum.SUCCEEDED.name())).collect(Collectors.toList());
        messagesTemplate.updateMessageStatuss(successMessages);

        //处理失败，超过重试次数
        List<MessagesDo> failedMessages = messagesDOS.stream().filter(message -> Objects.equals(message.getStatus(), MessagesStatusEnum.FAILED.name())).collect(Collectors.toList());
        messagesTemplate.updateMessageStatuss(failedMessages);

        //处理失败，未超过重试次数，retryCount++
        List<Long> retryMessageIds = messagesDOS.stream().filter(message -> Objects.equals(message.getStatus(), MessagesStatusEnum.PENDING.name()))
                .map(MessagesDo::getId).collect(Collectors.toList());
        messagesTemplate.incrRetryCountByIds(retryMessageIds);
    }


    /**
     * 处理消息
     *
     * @param message
     */
    public abstract void processMessage(MessagesDo message);


}
