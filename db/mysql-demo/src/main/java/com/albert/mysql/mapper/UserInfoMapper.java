package com.albert.mysql.mapper;

import com.albert.mysql.model.entity.UserInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Albert
 * @date 2020/10/29 21:04
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {

    /**
     * 简单查询
     */
    @Select("SELECT id,username,sex,property,money,create_time as createTime,timestamp FROM user")
    List<UserInfoPO> getUserInfoList();


}
