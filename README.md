spring-cloud案例项目
因本项目基于spring-cloud，故需要创建spring-cloud的根项目。
基于SpringCloud的项目，具体模块创建信息如下：
1. discovery模块(分布式部署必须)：主要就是为了能够
    完成EurekaServer 的服务注册。在该项目创建过程中，通过使用
    @EnableEurekaServer注解启动一个服务注册中心提供给其他应用
    进行对话；同时为了防止服务注册中心将自己作为客户端来尝试注册
    它自己，所以我们需要禁用它的客户端注册行为。注册中心声明后，
    所有模块通过@EnableDiscoveryClient注解激活Eureka中的
    DiscoveryClient实现，完成服务提供端的注册发现。

2. config模块(分布式部署必须)：服务配置（全局配置中心）config
    项目提供了一个解决分布式系统的配置管理方案。该模块分为
    config-server 配置服务端，管理Git或svn的外部配置，集中
    配置到所有客户端。和config-client 客户端，根据Spring框架的
    Environment和PropertySource从Config Sever获取配置客户端调
    用server端暴露接口获取配置信息。

3. user 模块：用户信息模块，用于完成用户相关接口的封装，本例中
    只用作案例使用
4. movie 模块：movie案例模块，使用ribbon方式完成接口的调用，本
    例中只用作测试user案例使用
5. movieFeign 模块：movieFeign案例模块，使用Feign方式完成接口
    的调用，本例中只用作测试user案例使用


6. api-gateway模块：主要是为了统一暴露接口而生，服务众多的情况下，
对前端来讲，我不需要记住那么多的域名地址来调用API，我需要
记住的只是gateway的地址就行。