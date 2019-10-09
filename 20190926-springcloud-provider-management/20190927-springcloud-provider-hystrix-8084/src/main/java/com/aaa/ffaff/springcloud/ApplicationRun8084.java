package com.aaa.ffaff.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ffaff
 * @date Created in 2019/9/28 19:44
 * @description  @EnableCircuitBreaker 开启断容器
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.aaa.ffaff.springcloud.mapper")
@EnableCircuitBreaker
public class ApplicationRun8084 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8084.class,args);
    }
}
