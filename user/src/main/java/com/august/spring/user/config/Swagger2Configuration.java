package com.august.spring.user.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * PROJECT_NAME: spring-cloud-example
 * PACKAGE_NAME: com.august.spring.user.config
 * Author: August
 * Update: August(2017/3/29)
 * Description:Swagger2配置文件，用于实现离线API的生成
 * 常用注解如下：
 * ①@Api注解 用来表述该服务的信息，如果不使用则显示类名称.
 * ②@ApiOperation注解 用于表述接口信息
 * ③@ApiParam注解 用于描述接口的参数
 * Swagger2默认将所有的Controller中的RequestMapping方法都会暴露，然而在实际开发中，我们并不一定需要把所有API都提现在文档中查看，这种情况下，使用注解@ApiIgnore来解决，如果应用在Controller范围上，则当前Controller中的所有方法都会被忽略，如果应用在方法上，则对应用的方法忽略暴露API。
 * <p>
 * 注解@ApiOperation和@ApiParam可以理解为API说明，多动手尝试就很容易理解了。
 * 如果我们不使用这样注解进行说明，Swagger2也是有默认值的，没什么可说的试试就知道了。
 * <p>
 * 在 http://localhost:8080/swagger-ui.html 显示页面的右上角有api_key ，springfox-swagger 2.2.2版本并没有进行处理，我们可以自己添加拦截器拦截 /v2/api-docs来处理我们API文档的访问权限，如果要更严格更灵活的控制，可能需要修改源码来实现了。相信 springfox-swagger 的后期版本应该会支持更全面的应用需求的。
 */
@Configuration
@EnableSwagger2
//当系统中配置允许进行swagger2的配置时，方可进行API的对外开放操作
@ConditionalOnExpression(value = "${swagger2.ui.enabled}")
public class Swagger2Configuration {

    @Value("${swagger2.ui.enabled}") //该配置项在全局配置中心管理
    private boolean environmentSpecificBooleanFlag;

    @Bean
    public SecurityScheme apiKey() {
        return new ApiKey("access_token", "accessToken", "header");
    }

    @Bean
    public Docket createTestRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
                    return true;
                if (input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户管理相关接口分组")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()// 选择哪些路径和API会生成document
                .apis(RequestHandlerSelectors.basePackage("com.august.spring"))// 对所有api进行监控
                .paths(Predicates.<String>alwaysTrue())//过滤的接口// 对所有路径进行监控预警周期	实时、周期（实时不需要设置值，周期则内嵌多个周期值或者新建集合）
                .build()
                .apiInfo(apiInfo())// 调用apiInfo方法,创建一个ApiInfo实例,里面是展示在文档页面信息内容
//                .securitySchemes(Arrays.asList(apiKey()))
                .enable(environmentSpecificBooleanFlag)
                ;
    }


    private ApiInfo apiInfo() {
        //创建作者信息以及联系方式
        Contact contact = new Contact("August-Lees", "http://august.com", "augustLees@github.com");
        return new ApiInfoBuilder()
                .title("SpringCloud服务案例之用户管理模块")//大标题
                .description("SpringCloud服务案例之用户管理模块 REST_FUL 列表")//小标题
                .termsOfServiceUrl("http://www.handu.com/hapm")
                .contact(contact)//作者信息以及联系方式
                .license("奥格斯工作室")//设置文档的License信息
                .licenseUrl("http://www.handu.com")//网站链接
                .version("1.0")//版本
                .build();
    }
}