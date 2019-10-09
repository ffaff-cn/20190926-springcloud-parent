package com.aaa.ffaff.springcloud.controller;

import com.aaa.ffaff.springcloud.model.User;
import com.aaa.ffaff.springcloud.service.IUserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/28 20:01
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/userAll")
    //@HystrixCommand(fallbackMethod = "selectAllUsersFallBack")
    public Map<String,Object> selectAllUsers() throws Exception {
        Map<String, Object> resultMap = userService.selectAll();
        if (200 == (Integer) resultMap.get("code")){
            //说明从数据库获取到了数据，现在故意让这个方法抛出异常，让断容器运行
            throw new Exception("获取用户信息异常，请稍后再试");
        }
        return resultMap;
    }

    /**
     *  selectAllUsers 的后备方法，当selectAllUsers方法出现异常时，就会调用本方法
     * @return
     */
    public Map<String,Object> selectAllUsersFallBack(){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        User user = new User();
        user.setId(1000);
        user.setUsername("熔断测试--->用户名");
        user.setPassword("熔断测试--->密码");
        user.setSalt("熔断测试--->盐值");
        resultMap.put("code",200);
        resultMap.put("result",user);
        return resultMap;
    }
}
