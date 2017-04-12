package com.august.spring.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.apiGateway
 * Author: August
 * Update: August(2017/3/25)
 * Description:应用程序网关主入口
 */
@SpringBootApplication
@EnableSidecar  //@EnableSidecar，这个东西他提供了一个jsp页面。
                // 通过这个页面我们可以知道gateway以及其他在eureka server
                // 上注册的服务的健康状况，并且这个注解包含了@EnableZuulProxy，
                // 所以呢，它也支持软负载均衡，如果启动多个服务，通过gateway来
                // 调用这个接口，多次调用我们会发现，请求会落在不同的服务上 。
public class APIGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }
}
