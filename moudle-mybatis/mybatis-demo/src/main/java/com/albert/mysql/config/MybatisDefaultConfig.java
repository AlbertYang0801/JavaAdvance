package com.albert.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 默认主数据源配置类
 * @author Albert
 */
@Primary
@Configuration
@MapperScan(basePackages = {"com.albert.mysql.*.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisDefaultConfig {

    @Resource
    @Qualifier("default")
    private DataSource defaultDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(defaultDataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory1());
    }

    /**
     * 创建事务管理器
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(defaultDataSource);
    }


}
