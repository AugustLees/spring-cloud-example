package com.august.spring.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.movie
 * Author: August
 * Update: August(2017/4/7)
 * Description:使用@FeignClient("user")注解绑定user服务，
 * 还可以使用url参数指定一个URL。
 * 在Feign中使用Hystrix是非常简单的事情，因为Feign已经集成了Hystrix。
 * 我们使用@FeignClient注解的fallback属性，指定fallback类
 */
@FeignClient(name = "user", fallback = UserFeignClient.HystrixClientFallback.class)
public interface UserFeignClient {
    @RequestMapping("/{id}")
    public User findByIdFeign(@RequestParam("id") Long id);

    /**
     * 这边采取了和Spring Cloud官方文档相同的做法，将fallback类作为内部类
     * 放入Feign的接口中，当然也可以单独写一个fallback类。
     *
     * @author eacdy
     */
    @Component
    static class HystrixClientFallback implements UserFeignClient {
        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

        /**
         * hystrix fallback方法
         *
         * @param id id
         * @return 默认的用户
         */
        @Override
        public User findByIdFeign(Long id) {
            HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
            User user = new User();
            user.setId(-1L);
            user.setUsername("default username");
            user.setAge(0);
            return user;
        }
    }
}
