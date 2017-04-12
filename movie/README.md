spring-cloud案例项目之movie 案例管理
movie主要用于movie 案例服务的管理。
开发过程如下:
基于SpringCloud的项目，具体模块创建信息如下：
1. 首先创建movie 案例管理项目模块movie模块,该模块基于maven项目创建。
2. 配置spring-cloud的依赖信息，添加pom文件中的SpringBoot依赖信息
    以及相应的项目创建信息；
3. 创建应用程序主入口，并使用@SpringBootApplication
    和@EnableDiscoveryClient完成自动注入。其中，
    @EnableDiscoveryClient定义了该模块为项目的movie案例服务管理客户端，用于
     完成movie案例服务管理服务的Eureka注册。
4. bootstrap.yml配置参照文件，这里只配置了服务的名字叫 movie.以及集中管
    理配置中心的地址
5. application.yml配置参照文件
   server.port配置了tomcat的启动端口，eureka注册中心地址，以及ribbon
   其他配置。因为我们这里是eureka的client端，所以只需要配置注册中心地址
   信息即可。   defaultZone 默认地址为 http://discovery:8761/eureka/
   该模块基于ribbon，主要用于完成客户端的负载均衡，Ribbon可以提供很多
   HTTP和TCP的控制行为
   
6. 在本模块根目录下定义docker文件夹下的Dokerfile文件。并配置基本的镜像
生成信息。