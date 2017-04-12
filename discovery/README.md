spring-cloud案例项目之服务发现
discovery主要用于服务的注册。
本项目使用Eureka进行注册服务的发现；Eureka简介：
一个RESTful服务，用来定位运行在AWS地区（Region）中的中间层服务。
由两个组件组成：Eureka服务器和Eureka客户端。Eureka服务器用作服
务注册服务器。Eureka客户端是一个Java客户端，用来简化与服务器的交
互、作为轮询负载均衡器，并提供服务的故障切换支持。Netflix在其生
产环境中使用的是另外的客户端，它提供基于流量、资源利用率以及出错
状态的加权负载均衡。
开发过程如下:
基于SpringCloud的项目，具体模块创建信息如下：
1. 首先创建服务发现项目模块discovery模块,该模块基于maven项目创建。
2. 配置spring-cloud的依赖信息，添加pom文件中的Springboot依赖信息
    以及相应的项目创建信息；
3. 创建应用程序主入口，并使用@SpringBootApplication
    和@EnableEurekaServer完成自动注入。其中，
    @EnableEurekaServer定义了该模块为项目的eureka注册服务器，用于
     完成相应的服务的注册。
4. bootstrap.yml配置参照文件，这里只配置了服务的名字叫 discovery.
5. application.yml配置参照文件
   server.port配置了tomcat的启动端口，eureka实例的名字，以及eureka
   其他配置。因为我们这里是eureka服务端，register-with-eureka配置
   为false，这个配置表示是否将其本身注册到eureka server以被其他发现
   fetch-registry配置为false，这个配置表示是否需要从eureka server
   中抓取eureka上的注册信息
   defaultZone 默认地址为 http://discovery:8761/eureka/
   
6. 在本模块根目录下定义docker文件夹下的Dokerfile文件。并配置基本的镜像
生成信息。