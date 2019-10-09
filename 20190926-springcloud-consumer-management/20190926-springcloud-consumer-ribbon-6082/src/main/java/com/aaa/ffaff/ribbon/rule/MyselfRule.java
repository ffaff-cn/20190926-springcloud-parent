package com.aaa.ffaff.ribbon.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ffaff
 * @date Created in 2019/9/27 18:57
 * @description
 *      这是自定义的配置ribbon负载均衡的类，所用到负载均衡算法为官方提供的，
 *    若需要自定义算法需要创建一个类继承AbstractLoadBalanceRule类，具体参考官方提供的算法
 *
 *    此处只能有@Configuration注解标明配置类，因为@SpringbootApplication注解中存在ComponentScan注解，
 *    会和ribbon的ComponentScan注解冲突，会报错！
 *    除非spring的ComponentScan比ribbon的ComponentScan版本高或者版本相同
 *
 */
@Configuration
public class MyselfRule {

    /**
     * 假定的自定义负载均衡算法，实际上是官方提供的
     * @return
     */
    @Bean
    public IRule getRandomRule(){
        return new RandomRule();
    }
}
