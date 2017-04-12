package com.august.spring.user.controller;

import com.august.spring.user.dao.UserRepository;
import com.august.spring.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.user.controller
 * Author: August
 * Update: August(2017/3/30)
 * Description:用户管理相关接口
 * 注解@RefreshScope指示Config客户端在服务器参数刷新时，也刷新注入的属性值
 */
@RestController
@RefreshScope
public class UserController {
    @Value("${spring.datasource.username}")
    String name = "World";

    @RequestMapping("/app-name")
    public String getAppName() {
        return "hello-world!";
    }

    //引入Eureka注册发现客户端实例
    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    //引入用户操作实例
    @Autowired
    private UserRepository userRepository;

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     * 注：@ RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     *
     * @param id 需要进行查找的用户ID
     * @return user信息
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findOne(id);
    }

    /**
     * 本地服务实例的信息
     *
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        return eurekaDiscoveryClient.getLocalServiceInstance();
    }


    //引入远程配置中心的配置信息
    @Value("${profile}")
    private String profile;

    @GetMapping("/hello")
    public String hello() {
        return this.profile;
//        return "dev";
    }
}
