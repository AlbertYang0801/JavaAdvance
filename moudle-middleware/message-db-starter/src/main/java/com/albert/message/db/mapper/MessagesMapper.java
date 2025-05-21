package com.albert.message.db.mapper;

import com.albert.message.db.entiry.MessagesDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-12
 */
@Mapper
public interface MessagesMapper extends BaseMapper<MessagesDo> {

    @Insert("insert into messages(topic, status, payload, enable_consume_time, retry_count, msg_id)" +
            "values ( #{topic}, #{status}, #{payload}, #{enableConsumeTime} , #{retryCount} , #{msgId}) ")
    void insertMessage(MessagesDo messagesDO);

    /**
     * 查询未消费的消息
     *
     * @param topic
     * @return
     */
    @Select("select * from messages where topic = #{topic} and status = 'PENDING' and enable_consume_time <= #{time}")
    List<MessagesDo> queryPendingMessages(@Param("topic") String topic, @Param("time") Long time);

    /**
     * 更新消息状态
     *
     * @param id
     * @param status
     * @return
     */
    @Update("update messages set status = #{status} where id = #{id}")
    boolean updateMessageStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 未处理记录加锁
     *
     * @param id
     * @return
     */
    @Select("select * from messages where id = #{id} and status = 'PENDING' for update")
    MessagesDo lockPendingMessage(@Param("id") Long id);

    @Update("update messages set retry_count = retry_count + 1 where id in " +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>")
    void incrRetryCountByIds(List<Long> retryMessageIds);


}
