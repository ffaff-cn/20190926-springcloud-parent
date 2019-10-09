package com.aaa.ffaff.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ffaff
 * @date Created in 2019/9/26 18:55
 * @description  @EnableDiscoveryClient:作用是让注册中心能够发现，扫描到该服务
 */
@SpringBootApplication
@MapperScan("com.aaa.ffaff.springcloud.mapper")
@EnableDiscoveryClient
public class ApplicationRun8081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8081.class,args);
    }
}
