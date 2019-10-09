package com.aaa.ffaff.springcloud;

import com.aaa.ffaff.ribbon.rule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author ffaff
 * @date Created in 2019/9/26 23:25
 * @description
 *      SpringBootApplication     springboot的启动配置
 *      EnableDiscoveryClient     eureka的客户端发现配置
 *      RibbonClient     ribbon的自定义负载均衡算法配置
 */
@SpringBootApplication
@EnableDiscoveryClient
//@RibbonClient(name = "USER-PROVIDER",configuration = MyselfRule.class)
public class ApplicationRun6082 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6082.class,args);
    }
}
