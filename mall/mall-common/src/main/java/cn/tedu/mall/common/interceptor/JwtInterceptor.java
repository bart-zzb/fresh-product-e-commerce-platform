package cn.tedu.mall.common.interceptor;


import cn.tedu.mall.common.constant.JwtConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.util.JwtUtils;
import cn.tedu.mall.common.web.JsonResult;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // OPTIONS 方法容易出现误判
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String header = request.getHeader(JwtConstants.AUTHORIZATION);

        if (!StringUtils.isBlank(header)) {
            String token = header.substring(JwtConstants.AUTHORIZATION_BEARER.length());
            JwtUtils.verify(token);
            return true;
        }

        response.getWriter().write(JSONObject.toJSONString(JsonResult.fail(ServiceCode.ERR_JWT_NOT_EXIST, ServiceConstant.JWT_NOT_FOUND)));
        return false;
    }
}
