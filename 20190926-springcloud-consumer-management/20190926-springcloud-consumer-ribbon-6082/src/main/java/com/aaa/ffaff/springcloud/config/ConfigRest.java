package com.aaa.ffaff.springcloud.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 配置随机算法
     * @return IRule接口是所有负载均衡算法的接口
     *      当不配置负载均衡算法时，默认为轮询算法（当检测不到算法时）
     *      一旦检测到有其他算法，则该算法为负载均衡的算法
     *
     *      这是一种负载均衡的实现，但是耦合度较高，因为该算法和RestTemplate注解进行了绑定
     *      根据职责单一化原则，需要将负载均衡算法和getRestTemplate分开，单独封装在一个类中
     *      官方中有说明：自定义的类不能放在@ComponentScan所扫描的当前包以及子包下，否则我们
     *              自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是我们达不到特殊化指定的目的了
     *              即不能再com.aaa.ffaff.springcloud包下
     */
//    @Bean
//    public IRule getRandomRule(){
//        return new RandomRule();
//    }

    /**
     *最小活跃数算法负载均衡：那台节点的连接数最少就选哪台
     * @return
     */
//    @Bean
//    public IRule getBestAvailableRule(){
//        return new BestAvailableRule();
//    }



}
