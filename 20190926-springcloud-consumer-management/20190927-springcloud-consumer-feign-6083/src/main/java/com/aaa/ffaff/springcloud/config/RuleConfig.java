package com.aaa.ffaff.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

/**
 * @author ffaff
 * @date Created in 2019/9/28 21:59
 * @description
 */
@SpringBootApplication
public class RuleConfig {
    @LoadBalanced
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }
}
