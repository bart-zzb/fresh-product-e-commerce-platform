package cn.tedu.mall.front.interceptor;


import cn.tedu.mall.common.constant.JwtConstants;
import cn.tedu.mall.common.util.JwtUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader(JwtConstants.AUTHORIZATION);
//        if ("OPTIONS".equals(request.getMethod())) {
//            return true;
//        }
        if (!StringUtils.isBlank(header)) {
            String token = header.substring(JwtConstants.AUTHORIZATION_BEARER.length(), header.length());
            JwtUtils.verify(token);
            return true;
        }

        return false;
    }
}
