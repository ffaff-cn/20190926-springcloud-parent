package com.aaa.ffaff.springcloud.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author ffaff
 * @date Created in 2019/9/26 19:32
 * @description
 *           因为ConfigRest是配置类，所以必须要加上@Configuration/@SpringBootApplication 注解
 *           RestTemplate:就是模拟了Http协议，使两个controller之间实现调用
 */
@SpringBootApplication
public class ConfigRest {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
