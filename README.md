端口列表：

header 1 | header 2
---|---
|springboot-jpa| 18080
|test-jpa|
|springboot-common|
|test-mybatis|
|test-mybatis-multi|
|test-jpa-thymeleaf|
|test-scheduler| 
|test-shiro|
|test-canal| 18080
|test-basic| 
|test-elasticsearch| 18081
```
mvn clean package install -pl com.mymusic:test-canal
# 运行
java -jar test-canal-1.0-SNAPSHOT.jar
```