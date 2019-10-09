package com.aaa.ffaff.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/userAll")
    public Map selectAllUsers(){
       /*
        * springcloud 远程调用是通过consumer的controller调用provider的controller实现的（http协议）
        * 此处通过RestTemplate对象模拟http协议调用provider的controller
        * getForObject方法需要两个参数：
        *    第一个参数:url(请求路径(localhost:8081/userAll))
        *    第二个参数:规定返回值的类型
        */
        return restTemplate.getForObject("http://localhost:8081/userAll", Map.class);
    }
}
