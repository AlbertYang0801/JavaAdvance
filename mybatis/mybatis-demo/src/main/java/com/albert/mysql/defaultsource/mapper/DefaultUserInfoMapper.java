package com.albert.mysql.defaultsource.mapper;

import com.albert.mysql.model.UserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Albert
 * @date 2020/10/29 21:04
 */
@Mapper
public interface DefaultUserInfoMapper {

    /**
     * 简单查询
     */
    @Select("SELECT id,name,age,timestamp FROM user_info")
    List<UserInfoPO> getUserInfoList();

    @Select("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    @Update("ALTER TABLE ${tableName} DISCARD TABLESPACE")
    void discareTables(@Param("tableName")String tableName);

    @Update("ALTER TABLE ${tableName} IMPORT TABLESPACE")
    void importTables(@Param("tableName")String tableName);

}
