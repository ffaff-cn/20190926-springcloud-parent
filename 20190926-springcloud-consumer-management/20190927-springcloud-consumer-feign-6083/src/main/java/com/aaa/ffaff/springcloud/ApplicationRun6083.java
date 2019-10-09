package com.aaa.ffaff.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ffaff
 * @date Created in 2019/9/27 20:53
 * @description
 *      EnableDiscoveryClient : 开启eureka的客户端发现
 *      EnableFeignClients ： 开启feign的功能
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationRun6083 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6083.class,args);
    }
}
