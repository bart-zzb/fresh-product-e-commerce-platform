package cn.tedu.mall.front.configuration;

import cn.tedu.mall.front.interceptor.JwtInterceptor;
import cn.tedu.mall.front.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //注意这个变动项，这个是旧版
                //.allowedOrigins("*")
                //这个才是新版的要求
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    //自定义Resolver
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/admin/**");
        //配置测试接口, 避免测试时被拦截
        excludePathList.add("/mall/category/**");
        excludePathList.add("/mall/cart/**");
        excludePathList.add("/mall/order/**");
        excludePathList.add("/mall/carousel/**");
        excludePathList.add("/mall/banner/**");
        excludePathList.add("/mall/label/**");
        //excludePathList.add("/mall/live/**");
        excludePathList.add("/mall/product/**");
        excludePathList.add("/mall/brand/**");
        excludePathList.add("/mall/product_specs/**");
        //配置图片库
        excludePathList.add("/carousel/**");
        excludePathList.add("/banner/**");
        excludePathList.add("/label/**");
        excludePathList.add("/live/**");
        excludePathList.add("/product/**");
        excludePathList.add("/live_card/**");
        //配置相关Knife4j地址不用被拦截
        excludePathList.add("/doc.html/**");
        excludePathList.add("/swagger-resources/**");
        excludePathList.add("/webjars/**");
        excludePathList.add("/v2/**");
        excludePathList.add("/v3/**");
        excludePathList.add("/swagger-ui.html/**");
        excludePathList.add("/api");
        excludePathList.add("/api-docs");
        excludePathList.add("/api-docs/**");

        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/mall/**")
                .excludePathPatterns(excludePathList);
    }

}
