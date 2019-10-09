package com.aaa.ffaff.springcloud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ffaff
 * @date Created in 2019/10/7 21:35
 * @description
 */
@SpringBootApplication
@MapperScan("com.aaa.ffaff.springcloud.mapper")
public class ApplicationRun3081 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun3081.class,args);
    }
}
