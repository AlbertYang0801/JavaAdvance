package com.albert.mysql.mapper;

import com.albert.mysql.model.po.TestData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * DOSM - 数据字典表
 * @author Albert
 * @date 2021/4/15 上午11:22
 */
@Mapper
public interface TestMapper extends BaseMapper<TestData> {

    @Select("SELECT id,data from test")
    TestData getData();


}

