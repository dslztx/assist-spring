不要作为一个Spring Boot Starter包：
1. Spring Boot启动时，检测到类路径下有web mvc的相关类，会进行一系列操作，导致不能正常运转
2. 已经有成熟的Starter，不要重复造轮子
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
