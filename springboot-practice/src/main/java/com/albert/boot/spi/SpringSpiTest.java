package com.albert.boot.spi;

import com.albert.boot.SpringBootPracticeApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author yjw
 * @time 2024/6/27 20:59
 */
public class SpringSpiTest {

    public static void main(String[] args) {
        List<DbDriver> dbDrivers = SpringFactoriesLoader.loadFactories(DbDriver.class, SpringBootPracticeApplication.class.getClassLoader());
        for (DbDriver dbDriver : dbDrivers) {
            System.out.println(dbDriver);
            dbDriver.buildDriver();
        }
        System.out.println("----");
        List<String> loadFactoryNames = SpringFactoriesLoader.loadFactoryNames(DbDriver.class, SpringBootPracticeApplication.class.getClassLoader());
        for (String loadFactoryName : loadFactoryNames) {
            System.out.println(loadFactoryName);
        }
    }


}
