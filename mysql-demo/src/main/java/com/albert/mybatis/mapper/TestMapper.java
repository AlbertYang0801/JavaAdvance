package com.albert.mybatis.mapper;

import com.albert.mybatis.model.po.TestData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * DOSM - 数据字典表
 * @author Albert
 * @date 2021/4/15 上午11:22
 */
@Mapper
public interface TestMapper {

    @Select("SELECT id,data from test")
    TestData getData();


}

