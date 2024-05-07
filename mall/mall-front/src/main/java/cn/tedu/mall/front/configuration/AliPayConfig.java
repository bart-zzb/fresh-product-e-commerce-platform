package cn.tedu.mall.front.configuration;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "alipay")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AliPayConfig {
    // 支付宝的AppId
    String appId;
    // 应用共钥
    String appPrivateKey;
    // 支付宝公钥
    String alipayPublicKey;
    // 支付宝通知本地的接口完整地址
    String notifyUrl;
}
