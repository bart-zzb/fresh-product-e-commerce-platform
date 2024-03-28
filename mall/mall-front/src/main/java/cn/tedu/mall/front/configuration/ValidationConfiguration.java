package cn.tedu.mall.front.configuration;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;

/**
 * 验证框架相关配置
 */
@Slf4j
@Configuration
public class ValidationConfiguration {

    public ValidationConfiguration(){
        log.debug("创建验证框架配置类-ValidationConfiguration");
    }

    @Bean
    public javax.validation.Validator validator(){
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true) //快速失败
                .buildValidatorFactory()
                .getValidator();
    }
}
