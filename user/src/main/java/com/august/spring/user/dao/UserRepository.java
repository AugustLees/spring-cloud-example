package com.august.spring.user.dao;

import com.august.spring.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.user.dao
 * Author: August
 * Update: August(2017/4/7)
 * Description:用户数据库操作层相关信息
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}