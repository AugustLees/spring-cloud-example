package com.august.spring.turbine;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.turbine
 * Author: August
 * Update: August(2017/4/8)
 * Description:通过@EnableTurbine接口，激活对Turbine的支持。
 */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine  //通过@EnableTurbine接口，激活对Turbine的支持。
public class TurbineApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TurbineApplication.class).web(true).run(args);
    }
}
