package com.albert.mysql.mapper;

import com.albert.mysql.entity.MessagesDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-13
 */
public interface MessagesMapper extends BaseMapper<MessagesDo> {

    /**
     * 锁记录
     * SKIP LOCKED  跳过已锁定的行
     * @param id
     * @return
     */
    @Select("select * from messages where id = #{id} for update")
    MessagesDo lockQueryMessageDo(Long id);

    /**
     * 锁记录
     * SKIP LOCKED  跳过已锁定的行
     * @param id
     * @return
     */
    @Select("select * from messages where id = #{id} for update skip locked")
    MessagesDo lockQueryNoBolck(Long id);


    /**
     * 锁记录
     * SKIP LOCKED  跳过已锁定的行
     * @param id
     * @return
     */
    @Select("select * from messages where id = #{id} and status = 'PENDING' for update skip locked")
    MessagesDo lockMessageDo(Long id);

    @Update("update messages set status = 'SUCCEEDED' where id = #{id}")
    void updateStatus(Long id);


}
