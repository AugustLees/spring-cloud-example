spring-cloud案例项目之hystrix断路器
hystrix主要用做断路器,能够为断路器以及密闭闸门等分布式系统提供一套
通用型故障容错实现模式.
断路器可以防止一个应用程序多次试图执行一个操作，即很可能失败，允许它
继续而不等待故障恢复或者浪费 CPU 周期，而它确定该故障是持久的。断路
器模式也使应用程序能够检测故障是否已经解决。如果问题似乎已经得到纠正
，应用程序可以尝试调用操作。
断路器增加了稳定性和灵活性，以一个系统，提供稳定性，而系统从故障中恢
复，并尽量减少此故障的对性能的影响。它可以帮助快速地拒绝对一个操作，
即很可能失败，而不是等待操作超时（或者不返回）的请求，以保持系统的响
应时间。如果断路器提高每次改变状态的时间的事件，该信息可以被用来监测
由断路器保护系统的部件的健康状况，或以提醒管理员当断路器跳闸，以在打
开状态。
开发过程如下:
基于SpringCloud的项目，具体模块创建信息如下：
1. 首先创建断路器项目模块hystrix模块,该模块基于maven项目创建。
2. 配置spring-cloud的依赖信息，添加pom文件中的SpringBoot依赖信息
    以及相应的项目创建信息；
3. 创建应用程序主入口，并使用@SpringBootApplication
    和@EnableHystrixDashboard完成自动注入。其中，
    @EnableHystrixDashboard定义了该模块为项目的eureka注册服务器，用于
     完成相应的服务的注册。
4. bootstrap.yml配置参照文件，这里配置了服务的名字叫 hystrix。同时指定
    集中配置管理中的信息地址。
5. application.yml配置参照文件
   server.port配置了tomcat的启动端口，eureka实例的名字，以及eureka
   其他配置。因为我们这里是eureka服务端，register-with-eureka配置
   为false，这个配置表示是否将其本身注册到eureka server以被其他发现
   fetch-registry配置为false，这个配置表示是否需要从eureka server
   中抓取eureka上的注册信息
   defaultZone 默认地址为 http://discovery:8761/eureka/
   
6. 在本模块根目录下定义docker文件夹下的Dokerfile文件。并配置基本的镜像
生成信息。