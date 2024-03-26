package cn.tedu.mall.passport.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 小刀的配置类
 */
@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    /**
     * 【重要】指定Controller包路径
     */
    private static final String BASE_PACKAGE = "cn.tedu.mall";
    /**
     * 组名
     */
    private static final String GROUP_NAME = "同城生鲜";
    /**
     * 主机名
     */
    private static final String HOST = "http://localhost:8080";
    /**
     * 标题
     */
    private static final String TITLE = "同城生鲜-用户服务-在线API文档";
    /**
     * 简介
     */
    private static final String DESCRIPTION = "同城生鲜-用户服务-在线API文档";
    /**
     * 服务条款URL
     */
    private static final String TERMS_OF_SERVICE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private static final String CONTACT_NAME = "佛山小组";
    /**
     * 联系网址
     */
    private static final String CONTACT_URL = "https://gitee.com/bart-zzb/fresh-product-e-commerce-platform";
    /**
     * 联系邮箱
     */
    private static final String CONTACT_EMAIL = "3533272661@qq.com";
    /**
     * 版本号
     */
    private static final String VERSION = "1.0";

    //@Autowired
    //private OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfiguration() {
        log.debug("创建配置类对象：Knife4jConfiguration");
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(HOST)
                .apiInfo(apiInfo())
                .groupName(GROUP_NAME)
                .select()
                //basePackage 重要重要重要
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
                //.extensions(openApiExtensionResolver.buildExtensions(GROUP_NAME));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .contact(new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL))
                .version(VERSION)
                .build();
    }

}
