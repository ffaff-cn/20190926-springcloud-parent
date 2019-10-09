package com.aaa.ffaff.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/26 19:30
 * @description consumer的controller远程调用provider的controller
 */
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private static final String URL="http://USER-PROVIDER";

    @RequestMapping("/userAll")
    public Map selectAllUsers(){
       /*
        * springcloud 远程调用是通过consumer的controller调用provider的controller实现的（http协议）
        * 此处通过RestTemplate对象模拟http协议调用provider的controller
        * getForObject方法需要两个参数：
        *    第一个参数:url(请求路径(localhost:8081/userAll))
        *    第二个参数:规定返回值的类型
        */
        return restTemplate.getForObject(URL+"/userAll", Map.class);
    }

    /**
     * 实现了ribbon脱离eureka 的负载均衡，但是此处会获取不到数据，即连接provider的controller不成功
     *  但实际上已经实现了负载均衡，只是因为脱离eureka后，localhost映射不到主机
     *  需要通过花生壳等（外网映射工具），并且在window的C盘-->window-->system32-->drivers-->etc-->hosts中配置localhost和IP地址的映射
     * @return
     */
    @RequestMapping("/allUser")
    public Map selectAllUserByLoadBanlance(){
        //1.通过loadBalancerClient对象获取到所有的服务提供者的信息
            //在application.properties配置文件中有一个配置user-provider.ribbon.listOfServers的值
            //通过在8081,8082,8083中配置的spring.application.name的值进行获取
            //serviceId-->就是spring.application.name配置的值（也是源码中的Object key 的值）
            // serviceInstance:Server 对象
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-provider");
        //2.获取server的IP地址
        String host = serviceInstance.getHost();
        System.out.println(host);
        //3.获取server的port端口号
        int port = serviceInstance.getPort();
        System.out.println(port);
        return restTemplate.getForObject("http://"+host+":"+port+"/userAll",Map.class);
    }
}
