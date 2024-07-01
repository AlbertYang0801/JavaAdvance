package com.albert.mysql;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.querys.ClickHouseQuery;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author jiangwei
 * @description: 需要将mybatis-plus-generator 版本改成3.5.1
 * @date 2024/4/11 15:36
 * @version: 1.0
 */

public class MySqlGenerator {

    private static final String REPLACE_LOCAL_PATH = "/target/test-classes/";
    private static final String JAVA_PATH = "/src/main/java";

    private static final String JDBC_URL = "jdbc:mysql://11.166.82.139:2881/yiqi_sc_meter?useUnicode=true";
    private static final String USERNAME = "root@atg_ob0_1437";
    private static final String PASSWORD = "obatg1";
    private static final String DB = "yiqi_sc_meter";


    public static void main(String[] args) {

        // 获取项目路径
        String projectPath = ClassLoader.getSystemResource("").getPath().replace(REPLACE_LOCAL_PATH, "");
        // 全局配置
        GlobalConfig gc = GeneratorBuilder.globalConfigBuilder()
                .fileOverride()
                .outputDir(projectPath + JAVA_PATH)
                .author("yangjunwei")
                .enableSwagger()
                .commentDate("yyyy-MM-dd").build();


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder(JDBC_URL, USERNAME, PASSWORD)
                .dbQuery(new MySqlQuery()).schema(DB).build();

        // 包配置
        PackageConfig pc = GeneratorBuilder.packageConfigBuilder().parent("org.yjw.mysql.mysql")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/mysql/"))
                .build();

        // 策略配置
        StrategyConfig strategy = GeneratorBuilder.strategyConfigBuilder()
                .addInclude("task_config","task_record")
                .addTablePrefix(pc.getModuleName() + "_")
                .controllerBuilder().enableHyphenStyle()
                .entityBuilder()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .versionColumnName("version")
                .logicDeleteColumnName("isDelete")
                .formatFileName("%sDo")
                .enableLombok()
                .build();

        TemplateConfig templateConfig = GeneratorBuilder.templateConfigBuilder()
                //不生成controller
                .controller("")
                .build();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(dsc).global(gc).strategy(strategy).template(templateConfig).packageInfo(pc);

        mpg.execute();
    }



}
