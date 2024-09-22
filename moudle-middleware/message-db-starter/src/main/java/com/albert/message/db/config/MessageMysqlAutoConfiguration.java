package com.albert.message.db.config;

import com.albert.message.db.mapper.MessagesMapper;
import com.albert.message.db.service.MessagesTemplate;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 消息中间件-mysql实现自动配置类
 *
 * @author yangjunwei
 * @date 2024/9/19
 */
@Configuration
@ConditionalOnProperty(
        prefix = "message",
        value = {"datasource.url"}
)
//手动注入properties的配置
@EnableConfigurationProperties(MessageDataSourceProperties.class)
@MapperScan(basePackages = "com.albert.message.db.mapper", sqlSessionFactoryRef = "messagesSqlSessionFactory")
public class MessageMysqlAutoConfiguration {

    @Resource
    MessageDataSourceProperties messageDataSourceProperties;

    @Bean
    public DataSource messageDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(messageDataSourceProperties.getDriverClassName());
        dataSource.setJdbcUrl(messageDataSourceProperties.getUrl());
        dataSource.setUsername(messageDataSourceProperties.getUsername());
        dataSource.setPassword(messageDataSourceProperties.getPassword());
        return dataSource;
    }

    @Bean
    public SqlSessionFactory messagesSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(messageDataSource());
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate messageSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(messagesSqlSessionFactory());
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(messageDataSource());
    }

    /**
     * mybatis-plus 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    @Bean
    public MessagesTemplate messagesTemplate(@Autowired MessagesMapper messagesMapper){
        return new MessagesTemplate(messagesMapper);
    }



}
