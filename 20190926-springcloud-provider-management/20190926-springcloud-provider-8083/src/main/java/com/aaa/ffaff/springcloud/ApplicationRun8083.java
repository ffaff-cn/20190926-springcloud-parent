package com.aaa.ffaff.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ffaff
 * @date Created in 2019/9/26 23:59
 * @description
 */
@SpringBootApplication
@MapperScan("com.aaa.ffaff.springcloud.mapper")
@EnableDiscoveryClient
public class ApplicationRun8083 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8083.class,args);
    }
}
