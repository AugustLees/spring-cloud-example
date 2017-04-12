package com.august.spring.configServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.configServer
 * Author: August
 * Update: August(2017/3/28)
 * Description:配置中心应用程序主入口
 * 通过@EnableConfigServer注解激活配置服务.
 * 说明：
 * 在application.yml中有个git.uri的配置，
 * 目前配置的是https://github.com/liping0000/spring-boot-config.git
 * 获取git上的资源信息遵循如下规则：
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 *
 * 例如本例：可使用以下路径来访问config-dev.properties：
 * http://localhost:8888/config-dev.properties
 * http://localhost:8888/config/dev
 */
@SpringBootApplication
@EnableDiscoveryClient  //实现该服务在Eureka中的注册
@EnableConfigServer     //声明该服务为全局配置中心服务器
public class ConfigApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ConfigApplication.class);
        Environment environment = springApplication.run(args).getEnvironment();
        try {
            LOGGER.info("\n----------------------------------------------------------\n\t" +
                            "Application '{}' is running! Access URLs:\n\t" +
                            "Local: \t\thttp://config:{} \n\t" +
                            "External: \thttp://{}:{} \n\t" +
                            "Profile(s): \t{}\n----------------------------------------------------------",
                    environment.getProperty("spring.application.name") + "(" + environment.getProperty("version") + ")",
                    environment.getProperty("server.port"),
                    InetAddress.getLocalHost().getHostAddress(),
                    environment.getProperty("server.port"),
                    environment.getProperty("spring.profiles.active"));
        } catch (UnknownHostException e) {
            LOGGER.error("UnknownHostException", e);
        }
    }
}
