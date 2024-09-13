package com.albert.db.mapper;

import com.albert.db.entiry.MessagesDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("update messages set retry_count = retry_count+1 where id in (#{retryMessageIds})")
    void incrRetryCountByIds(List<Long> retryMessageIds);

}
