spring-cloud案例项目之配置管理
config主要用于 配置服务端，服务管理配置信息,客户端调用server端暴
露接口获取配置信息。
SpringCloudConfig就是我们通常意义上的配置中心，把应用原本放在本
地文件的配置抽取出来放在中心服务器，从而能够提供更好的管理、发布
能力。SpringCloudConfig分服务端和客户端，服务端负责将git（svn）
中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配
置。但客户端并不能主动感知到配置的变化，从而主动去获取新的配置，这
需要每个客户端通过POST方法触发各自的/refresh。
开发过程如下:
基于SpringCloud的项目，具体模块创建信息如下：
1. 首先创建配置管理项目模块config模块,该模块基于maven项目创建。
2. 配置spring-cloud的依赖信息，添加pom文件中的SpringBoot依赖信息
    以及相应的项目创建信息；
3. 创建应用程序主入口，并使用@SpringBootApplication
    和@EnableConfigServer以及@EnableDiscoveryClient完成自动注入。其中，
    @EnableConfigServer定义了该模块为项目的config配置管理服务器，用于
     完成相应的服务的配置管理。
4. bootstrap.yml配置参照文件，这里只配置了服务的名字叫 config.
5. application.yml配置参照文件
   server.port配置了tomcat的启动端口，config实例的名字，以及config
   其他配置。因为我们这里是config服务端，register-with-eureka配置
   为true，这个配置表示是否将其本身注册到eureka server以被其他发现
   fetch-registry配置为true，这个配置表示是否需要从eureka server
   中抓取eureka上的注册信息
   defaultZone 默认地址为 http://discovery:8761/eureka/
   spring.cloud.config.server.git.uri为配置配置管理服务器的URI地址信息
   
6. 在本模块根目录下定义docker文件夹下的Dokerfile文件。并配置基本的镜像
生成信息。