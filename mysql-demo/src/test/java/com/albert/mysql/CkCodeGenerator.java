package com.albert.mysql;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.querys.ClickHouseQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author jiangwei
 * @description: 需要将mybatis-plus-generator 版本改成3.5.1
 * @date 2024/4/11 15:36
 * @version: 1.0
 */

public class CkCodeGenerator {

    private static final String REPLACE_LOCAL_PATH = "/target/test-classes/";
    private static final String JAVA_PATH = "/src/main/java";

    public static void main(String[] args) {

        // 获取项目路径
        String projectPath = ClassLoader.getSystemResource("").getPath().replace(REPLACE_LOCAL_PATH, "");
        // 全局配置
        GlobalConfig gc = GeneratorBuilder.globalConfigBuilder()
                .fileOverride()
                .outputDir(projectPath + JAVA_PATH)
                .author("jiangwei")
                .enableSwagger()
                .commentDate("yyyy-MM-dd").build();


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig.Builder("jdbc:clickhouse://10.10.101.69:38123/ebpf", "user2", "qwerty")
                .dbQuery(new ClickHouseQuery()).schema("ebpf").build();

        // 包配置
        PackageConfig pc = GeneratorBuilder.packageConfigBuilder().parent("org.vlis.cloudmonitor.server.clickhouse")
                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/clickhouse/"))
                .build();

        // 策略配置
        StrategyConfig strategy = GeneratorBuilder.strategyConfigBuilder()
                .addInclude("app_connection_type","app_java_managerment","app_java_exception","app_java_trace")
                .addTablePrefix(pc.getModuleName() + "_")
                .controllerBuilder().enableHyphenStyle()
                .entityBuilder()
                .enableTableFieldAnnotation()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel)
                .versionColumnName("version")
                .logicDeleteColumnName("isDelete")
                .formatFileName("%sDao")
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
