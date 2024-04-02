package cn.tedu.mall.common.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderUtils {
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String enc(String content) {
        return bCryptPasswordEncoder.encode(content);
    }

    public static boolean decrypt(String content, String encryptContent) {
        return bCryptPasswordEncoder.matches(content, encryptContent);
    }
}
