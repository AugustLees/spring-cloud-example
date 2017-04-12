package com.august.spring.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.movie
 * Author: August
 * Update: August(2017/4/7)
 * Description:
 */
@SpringBootApplication
@EnableFeignClients     //使用@EnableFeignClients开启Feign
@EnableDiscoveryClient  //实现该服务在Eureka中的注册
public class MovieFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieFeignApplication.class, args);
    }
}
