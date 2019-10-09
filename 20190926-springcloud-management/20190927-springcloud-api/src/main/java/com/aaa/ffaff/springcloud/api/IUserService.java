package com.aaa.ffaff.springcloud.api;

import com.aaa.ffaff.springcloud.fallback.IUserServiceFallbackFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author ffaff
 * @date Created in 2019/9/27 20:51
 * @description
 *      feign 的远程调用通过consumer中的接口映射到provider的controller，符合传统的MVC开发模式
 *          其中集成ribbon，feign的负载均衡就是由ribbon实现的，feign在ribbon的基础上进行了封装，使得远程调用
 *          符合开发者传统的MVC开发模式（无感调用）
 *      @FeignClient(value = "USER-PROVIDER")中的value就是eureka中APPLICATION的值
 *      feign通过@RequestMapping("/userAll")注解来和provider中controller中的@RequestMapping("/userAll")
 *          进行映射到具体的方法，因此该路径必须保持一致
 *
 *       ！！！注意：
 *              如果需要传参时：
 *                  当传的参数是基本数据类型时，需要在参数之前加@RequestParam注解
 *                  当传的参数是包装类型时，需要在参数之前加@RequestBody注解
 *              每个方法只能传一个参数，如果有多个参数，需要自己封装成实体类，以包装类型传递
 */
@FeignClient(value = "USER-PROVIDER",fallbackFactory = IUserServiceFallbackFactory.class)
public interface IUserService {

    @RequestMapping("/userAll")
    Map<String,Object> selectAll();
}
