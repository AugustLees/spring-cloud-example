package com.august.spring.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.movie
 * Author: August
 * Update: August(2017/4/7)
 * Description:
 */
@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return this.userFeignClient.findByIdFeign(id);
    }
}
