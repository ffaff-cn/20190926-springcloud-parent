package com.aaa.ffaff.springcloud.service.impl;

import com.aaa.ffaff.springcloud.mapper.UserMapper;
import com.aaa.ffaff.springcloud.model.User;
import com.aaa.ffaff.springcloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/26 19:12
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> selectAll() {
        List<User> userList = userMapper.selectAll();
        Map<String,Object> resultMap = new HashMap<String, Object>();
        if(userList.size() > 0){
            resultMap.put("code",200);
            resultMap.put("result",userList);
        }else {
            resultMap.put("code",404);
        }
        return resultMap;
    }

}
