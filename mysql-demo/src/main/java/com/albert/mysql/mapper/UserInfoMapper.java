package com.albert.mysql.mapper;

import com.albert.mysql.model.po.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Albert
 * @date 2020/10/29 21:04
 */
@Mapper
public interface UserInfoMapper {

    @Select("SELECT id,name from user_info")
    List<UserInfoPO> getUserInfoList();

}
