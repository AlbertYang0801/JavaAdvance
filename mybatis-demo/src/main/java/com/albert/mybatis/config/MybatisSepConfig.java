package com.albert.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * sqlServer数据源的配置类
 * @author Albert
 */
//@Configuration
//@MapperScan(basePackages = {"com.albert.mybatis.sqlserver.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory3")
//public class MybatisSepConfig {
//
//    @Resource
//    @Qualifier("sep")
//    private DataSource sepDb;
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory3() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(sepDb);
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate5() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory3());
//    }
//
//
//}
