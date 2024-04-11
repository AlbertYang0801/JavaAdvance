package com.albert.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Albert
 * @date 2020/9/7 17:15
 */
@MapperScan("com.albert.mysql.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class MysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }

}
