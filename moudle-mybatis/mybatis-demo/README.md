### springboot+mybatis集成多数据源


#### 1.mysql多数据源

* 启动类排除默认数据源DataSourceAutoConfiguration
* 设置配置文件application.properties的多数据源连接属性值
* 设置DatasourceConfig配置类，向容器中注入多数据源
* 设置单个数据源的配置类，设置该数据源扫描的mapper文件路径


#### 2.集成其他数据库（sqlServer）多数据源配置

* 类比mysql多数据源
* 设置配置文件application.properties添加其他数据库的数据源连接属性值
