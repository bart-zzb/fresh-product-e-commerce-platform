package cn.tedu.mall.front.interceptor;


import cn.tedu.mall.common.constant.JwtConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String header = request.getHeader(JwtConstants.AUTHORIZATION);
        
        if (!StringUtils.isBlank(header)) {
            String token = header.substring(JwtConstants.AUTHORIZATION_BEARER.length(), header.length());
            JwtUtils.verify(token);
            return true;
        }

        throw new ServiceException(ServiceCode.ERR_JWT_SIGNATURE, ServiceConstant.JWT_NOT_FOUND);
//        return false;
    }
}
