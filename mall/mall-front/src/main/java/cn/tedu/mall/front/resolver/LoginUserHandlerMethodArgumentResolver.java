package cn.tedu.mall.front.resolver;

import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.constant.JwtConstants;
import cn.tedu.mall.common.util.JwtUtils;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * 自定义处理方法参数解析器
 */
@Slf4j
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    //判断的条件
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(CurrentUser.class) && methodParameter.getParameterType().isAssignableFrom(CurrentPrincipal.class);
    }

    //当符合判断的条件后具体的方法
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        //header中获取用户token
        nativeWebRequest.getHeader(JwtConstants.AUTHORIZATION);
        Map<String, Object> userInfo = JwtUtils.getUserInfo(nativeWebRequest);
        CurrentPrincipal currentPrincipal = new CurrentPrincipal();
        log.debug("id:"+userInfo.get("id"));
        log.debug("username:"+userInfo.get("username"));
        currentPrincipal.setId(Long.parseLong((String) userInfo.get("id")));
        currentPrincipal.setUsername((String) userInfo.get("username"));
        return currentPrincipal;
    }
}
