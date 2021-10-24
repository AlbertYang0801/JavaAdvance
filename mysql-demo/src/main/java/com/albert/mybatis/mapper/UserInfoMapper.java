package com.albert.mybatis.mapper;

import com.albert.mybatis.model.po.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Albert
 * @date 2020/10/29 21:04
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 简单查询
     */
    @Select("SELECT id,username,sex,property,money,create_time as createTime,timestamp FROM user")
    List<UserInfoPO> getUserInfoList();


}
