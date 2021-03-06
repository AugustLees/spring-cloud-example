package com.august.spring.movie.hystrix.ribbon;

import lombok.Data;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.movie.hystrix.ribbon
 * Author: August
 * Update: August(2017/4/8)
 * Description:
 */
@Data
public class User {
    private Long id;
    private String username;
    private Integer age;
}
