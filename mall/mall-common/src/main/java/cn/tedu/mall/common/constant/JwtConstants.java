package cn.tedu.mall.common.constant;

/**
 * Jwt状态码
 */
public class JwtConstants {

    /**
     * 用户状态-未激活
     */
    public static final Integer USER_STATUS_UNVERIFIED = 0;
    /**
     * 用户状态-已激活
     */
    public static final Integer USER_STATUS_ACTIVE = 1;
    /**
     * 用户状态-已停用
     */
    public static final Integer USER_STATUS_BLOCK_UP = 2;
    /**
     * 用户状态-已注销
     */
    public static final Integer USER_STATUS_CANCEL = 3;

    public static final String USER_TYPE_NAME = "普通用户";

    // token 最后5分钟自动刷新
    public static final long REFRESH_TIME = 5 * 60 * 1000L;

    public static final String AUTHORIZATION = "Authorization";

    public static final String AUTHORIZATION_BEARER = "Bearer ";

    public static final String REFRESH_TOKEN_HEADER_NAME = "refresh_token";

    public static final Integer JWT_TIMEOUT = 30;

    public static final String SHOP_ID = "shopId";

    public static final String USER_ID = "id";

    public static final String PHONE = "phone";

    public static final String USER_NAME = "username";
}
