## grpc demo


### 安装 protobuf

```shell
brew install protobuf
```

### 配置编译插件

```xml

    <build>
        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.4.1.Final</version>
            </extension>
        </extensions>
        <plugins>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.5.0</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:3.12.0:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.34.1:exe:${os.detected.classifier}</pluginArtifact>
                    <!--设置grpc生成代码到指定路径-->
                    <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
                    <!--生成代码前是否清空目录-->
                    <clearOutputDirectory>false</clearOutputDirectory>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

            <!-- 设置多个源文件夹 -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <!-- 添加主源码目录 -->
                    <execution>
                        <id>add-source</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>add-source</goal>
                        </goals>
                        <configuration>
                            <sources>
                                <source>${project.basedir}/src/main/gen</source>
                                <source>${project.basedir}/src/main/java</source>
                            </sources>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>

```


### 编译Proto文件

生成反序列化类

使用 protobuf 插件，```protobuf:compile```


### 编译Grpc文件

编译Grpc使用文件
使用 protobuf 插件，```protobuf:compile-custom```