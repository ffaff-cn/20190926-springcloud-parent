package com.aaa.ffaff.springcloud.contorller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ffaff
 * @date Created in 2019/10/7 22:00
 * @description
 */
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @RequestMapping("/config")
    public String testConfig() {
        return port+"-----------------"+driverClassName;
    }

}
