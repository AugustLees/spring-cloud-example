package com.august.spring.discovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.discovery
 * Author: August
 * Update: August(2017/3/27)
 * Description:服务发现应用程序主入口
 */
@SpringBootApplication
//通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话
@EnableEurekaServer
public class DiscoveryApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DiscoveryApplication.class);
        Environment environment = springApplication.run(args).getEnvironment();
        try {
            LOGGER.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://discovery:{} \n\t" +
                            "External: \thttp://{}:{} \n\t" +
                            "Profile(s): \t{}\n----------------------------------------------------------",
                    environment.getProperty("spring.application.name") + "(" + environment.getProperty("version") + ")",
                    environment.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    environment.getProperty("server.port"),
                    environment.getProperty("active"));
        } catch (UnknownHostException e) {
            LOGGER.error("UnknownHostException", e);
        }
    }
}
