package com.albert.mysql.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 根据配置文件配置多数据源
 * @author Albert
 */
@Configuration
public class DatasourceConfig {

    /**
     * 默认mysql数据源
     */
    @Bean(name = "default")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource dataSourceDefault() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 另一个mysql数据源
     */
    @Bean(name = "albert")
    @ConfigurationProperties(prefix = "spring.datasource.albert")
    public DataSource dataSourceAlbert() {
        return DataSourceBuilder.create().build();
    }

//    /**
//     * sqlServer数据源
//     */
//    @Bean(name = "sep")
//    @ConfigurationProperties(prefix = "spring.datasource.sep")
//    public DataSource dataSourceSep() {
//        return DataSourceBuilder.create().build();
//    }


}
