package com.aaa.ffaff.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ffaff
 * @date Created in 2019/10/7 21:06
 * @description
 */
@SpringBootApplication
@EnableConfigServer
public class ApplicationRun4081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun4081.class,args);
    }
}
