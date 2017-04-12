package com.august.spring.movie.hystrix.ribbon;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.movie.hystrix.ribbon
 * Author: August
 * Update: August(2017/4/8)
 * Description:
 * 基于Ribbon的断路器管理下的模块信息
 * 使用@EnableCircuitBreaker注解开启断路器功能
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker   //注解开启断路器功能
@EnableZuulProxy        //@EnableZuulProxy注解激活zuul。
                        // 该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，
                        // 是个组合注解，目的是简化配置
public class MovieRibbonHystrixApplication {
    /**
     * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
     *
     * @return restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieRibbonHystrixApplication.class, args);
    }
}
