package com.aaa.ffaff.springcloud.controller;

import com.aaa.ffaff.springcloud.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/27 20:58
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/userAll")
    public Map<String,Object> selectAllUsers(){
        return userService.selectAll();
    }
}
