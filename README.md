# BlogSystem 🍅



**作者：**

**GitHub：[tumo](https://github.com/TyCoding)**

**Blog：[tumo**](http://tycoding.cn)

🍉🍉🍉🍉🍉



### 一、依赖版本

| dependency   | version |
| ------------ | ------- |
| Java         | 1.8     |
| MySQL        | 8.0.19  |
| SpringBoot   | 2.3.1   |
| mybatis-plus | 3.3.2   |
| shiro        | 1.5.2   |
| swagger      | 2.9.2   |
| ···          | ···     |

### 二、配置文件yml

```java
server:
  port: 8080
  tomcat:
    uri-encoding: utf-8

spring:
# 选择使用版本
  profiles:
    active: dev

  #thymeleaf模板引擎
  thymeleaf:
    cache: false

  #文件上传相关设置
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 100MB

mybatis-plus:
  type-aliases-package: com.lyzzz.admin.entity
  mapper-locations: classpath:mapper/*.xml
  configuration:
    jdbc-type-for-null: null
  global-config:
    banner: false
```

`数据库`

```java
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    username: root
    password: root
    url: jdbc:mysql://127.0.0.1:3306/tumo?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai <!--mysql8 需要设置Timezone--> 

```

### 三、common

···	

### 四、配置类

#### 		1.配置Swagger `com.lyzzz.common.config.SwaggerConfig`

```java
// 声明配置类
@Configuration
// 在dev使用Swagger2
@Profile("dev")
// 使用Swagger注解
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private BlogProperties blogProperties;

    @Bean
    public Docket createRestApi(){
        SwaggerProperties swagger = blogProperties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(swagger))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .build();
    }

    // apiInfo
    private ApiInfo apiInfo(SwaggerProperties swagger) {
        return new ApiInfoBuilder()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .termsOfServiceUrl(swagger.getUrl())
                .contact(new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()))
                .version(swagger.getVersion())
                .build();
    }
}
```

#### 		2.配置shiro `com.lyzzz.common.config.ShiroConfig`

##### 	2.1 AuthRealm

##### 	2.2 AuthSessionManagr

##### 	2.3 ShiroSessionListener

##### 	2.4 ShiroConfig











​				

