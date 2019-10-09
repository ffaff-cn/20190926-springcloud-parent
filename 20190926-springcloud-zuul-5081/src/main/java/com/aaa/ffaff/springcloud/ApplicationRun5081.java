package com.aaa.ffaff.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author ffaff
 * @date Created in 2019/10/7 19:52
 * @description
 *      zuul 的启动类
 *    EnableDiscoveryClient：eureka客户端的声明注解
 *    EnableZuulProxy：路由代理器的声明注解
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ApplicationRun5081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun5081.class,args);
    }
}
