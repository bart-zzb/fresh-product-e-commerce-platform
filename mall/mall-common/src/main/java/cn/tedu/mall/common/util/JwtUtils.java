package cn.tedu.mall.common.util;


import cn.tedu.mall.common.constant.JwtConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 封装生成token和验证token方法
 */
@Component
public class JwtUtils {
    /**
     * 生成私钥 token header.payload.signature
     */
    private final static String SIGNATURE = "token!Q2W#E$RW";

    @Autowired
    private HttpServletRequest request;

    public Map<String, Object> getUserInfo() {
        String header = request.getHeader(JwtConstants.AUTHORIZATION);
        String token = header.substring(JwtConstants.AUTHORIZATION_BEARER.length());
        DecodedJWT tokenInfo = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        Map<String, Claim> claims = tokenInfo.getClaims();
        Set<Map.Entry<String, Claim>> entries = claims.entrySet();
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Claim> entry : entries) {
            map.put(entry.getKey(), entry.getValue().as(Object.class));
        }
        //SpsAdminEntity spsAdminEntity = JSON.parseObject(JSON.toJSONString(map), SpsAdminEntity.class);
        return map;
    }

    /**
     * 生成token
     *
     * @param map map集合
     * @return token
     */
    public static String getToken(Map<String, Object> map) {
        Calendar instance = Calendar.getInstance();
        //30分钟过期
        instance.add(Calendar.MINUTE, JwtConstants.JWT_TIMEOUT);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload,用遍历避免多个键值对
        map.forEach((k, v) -> builder.withClaim(k, v + ""));
        //指定令牌过期时间,设置签名返回token
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGNATURE));
        return token;
    }

    /**
     * 验证token合法性
     * @param token 令牌
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    public static void main(String[] args) {

    }
}