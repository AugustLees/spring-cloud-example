package com.august.spring.user.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.user
 * Author: August
 * Update: August(2017/4/7)
 * Description:
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    private Integer age;
}
