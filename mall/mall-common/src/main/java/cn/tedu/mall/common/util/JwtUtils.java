package cn.tedu.mall.common.util;


import cn.tedu.mall.common.constant.JwtConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

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
    private static String SIGNATURE;

    @Value("${jwt.signature}")
    public void setSignature(String signature){
        SIGNATURE = signature;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private static HttpServletRequest request;

    public static Map<String, Object> getUserInfo(NativeWebRequest nativeWebRequest) {
        String header = nativeWebRequest.getHeader(JwtConstants.AUTHORIZATION);
        if(header == null){
            throw new ServiceException(ServiceCode.ERR_JWT_NOT_EXIST, ServiceConstant.JWT_NOT_FOUND);
        }
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
        //TODO 利用Redis存放token内的用户信息
        return token;
    }

    /**
     * 验证token有效性
     * @param token 令牌
     */
    public static void verify(String token) {
        //根据密钥生成算法示例再生成JWT校验器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();
        jwtVerifier.verify(token);
    }
}