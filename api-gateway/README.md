spring-cloud案例项目之api网关开发
api-gateway主要是为了统一暴露接口而生，服务众多的情况下，对前端来讲，
我不需要记住那么多的域名地址来调用API，我需要记住的只是gateway的地址
就行。
开发过程如下:
1. 首先创建api网关项目模块api-gateway,该模块基于maven项目创建。
2. 配置spring-cloud的依赖信息，添加pom文件中的SpringBoot依赖；
3. 创建应用程序主入口，并使用@SpringBootApplication和@EnableSidecar
完成自动注入。其中，@EnableSidecar，这个东西他提供了一个jsp页面。通过
这个页面我们可以知道gateway以及其他在eureka server上注册的服务的健康
状况，并且这个注解包含了@EnableZuulProxy，所以呢，它也支持软负载均衡，
如果启动多个服务，通过gateway来调用这个接口，多次调用我们会发现，请求会
落在不同的服务上 。
4. 配置application.yml文件，里面主要多了一个endpoints配置和sidecar的
端口配置，endpoints是为了监控系统才有的配置，具体可以参看
AbstractEndpoint.java的实现类。sidecar的端口这里配置的是8000，其他端口
也是可以的。
5. 配置bootstrap.yml，因为里面只有服务的名字，和上面的类似。
6. 在本模块根目录下定义docker文件夹下的Dokerfile文件。并配置基本的镜像
生成信息。

备注：
1. 如何查看服务的健康状况？
答: 就本例来说，我们访问地址http://gateway:10000/来了解gateway的情况，
如果要知道catelog-service的情况我们就访问
http://gateway:10000/hosts/服务名称地址。hosts后面跟我们的
服务的名字即可。
2. 如何通过gateway来访问其他服务？
答：举例，如果我们要通过gateway来访问服务名称服务，那么我在浏
览器里面输入http://gateway:10000/服务名称/v1/category这里
的/v1/category是由服务名称来决定的,服务名称则是服务的名字
我们可以总结出规律：访问
http://GATEWAY:GATEWAY_PORT/想要访问的Eureka服务id的小写/**
，将会访问到http://想要访问的Eureka服务id的小写:该服务端口/**