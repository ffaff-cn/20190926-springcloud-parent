package com.aaa.ffaff.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author ffaff
 * @date Created in 2019/9/26 20:19
 * @description  @EnableEurekaServer:作用是开启eureka的服务器端
 *          对应的有: @EnableEurekaClient:但这是springcloud 1.x 版本的注解，2.x版本中禁止使用，用@EnableDiscoveryClient注解代替
 */
@SpringBootApplication
@EnableEurekaServer
public class ApplicatonRun7081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicatonRun7081.class,args);
    }
}
