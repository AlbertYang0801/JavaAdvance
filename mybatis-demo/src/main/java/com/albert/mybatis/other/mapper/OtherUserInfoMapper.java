package com.albert.mybatis.other.mapper;

import com.albert.mybatis.model.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Albert
 * @date 2020/10/29 21:04
 */
@Mapper
public interface OtherUserInfoMapper {

    /**
     * 简单查询
     */
    @Select("SELECT id,name,age,timestamp FROM user_info")
    List<UserInfoPO> getUserInfoList();

}
