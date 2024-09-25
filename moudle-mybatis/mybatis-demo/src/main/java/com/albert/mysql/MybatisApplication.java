package com.albert.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.sql.DataSource;

/**
 * @author Albert
 * @date 2020/9/7 17:15
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
