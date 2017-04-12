package com.august.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring
 * Author: August
 * Update: August(2017/3/31)
 * Description:设置熔断器主程序入口
 * * 测试步骤:
 * 1. 访问http://localhost:7979/hystrix.stream 可以查看Dashboard
 * 2. 在上面的输入框填入: http://想监控的服务:端口/hystrix.stream进行测
 试
 * 注意：首先要先调用一下想监控的服务的API，否则将会显示一个空的图表.
 */
@SpringBootApplication
@EnableDiscoveryClient  //实现该服务在Eureka中的注册
@EnableHystrixDashboard //开启断路器功能
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
