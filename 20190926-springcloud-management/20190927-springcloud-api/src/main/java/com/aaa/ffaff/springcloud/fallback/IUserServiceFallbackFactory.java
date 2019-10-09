package com.aaa.ffaff.springcloud.fallback;

import com.aaa.ffaff.springcloud.api.IUserService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/29 16:52
 * @description
 */
@Component
public class IUserServiceFallbackFactory implements FallbackFactory<IUserService> {


    public IUserService create(Throwable throwable) {

        return new IUserService() {
            public Map<String, Object> selectAll() {
                System.out.println("测试熔断，IUservcie已经抛出异常");
                return null;
            }
        };
    }


}
