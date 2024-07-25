package com.albert.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 第二个数据源的配置
 * @author Albert
 */
@Configuration
@MapperScan(basePackages = {"com.albert.mybatis.other.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisAlbertConfig {

    @Resource
    @Qualifier("albert")
    private DataSource albertDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(albertDataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
      return new SqlSessionTemplate(sqlSessionFactory2());
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(albertDataSource);
    }


}
