package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.JwtUtils;
import cn.tedu.mall.common.util.PasswordEncoderUtils;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.dto.UserLoginByPsdDTO;
import cn.tedu.mall.service.service.IUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户模块")
@Slf4j
@Validated
@RequestMapping("/admin/user")
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/loginByUsernameAndPassword")
    public JsonResult loginByUserNameAndPassword(UserLoginByPsdDTO userLoginByPsdDTO,
                                                 HttpServletRequest request, HttpServletResponse response) {
        log.debug(userLoginByPsdDTO.getUsername() + userLoginByPsdDTO.getPassword());

        // 根据用户名去到数据库拿密码 TODO
        CurrentPrincipal currentPrincipal = userService.getCurrentPrincipalByUsernameAndPassword(userLoginByPsdDTO.getUsername(), userLoginByPsdDTO.getPassword());
        if(currentPrincipal==null){
            throw new ServiceException(ServiceCode.ERR_USERNAME_PASSWORD, ServiceConstant.ERROR_USERNAME_PASSWORD);
        }
        // sigPwd模拟从数据库拿出来的密码，改密码在注册时加密放入，数据库存储的是密文例：$2a$10$B4WcenV2GOOpIPVG/sQSWuZ04llGaDdQSOvzXfJtpMYOKZHpJNLx.

        // 结构解释了解即可：$2a$：这部分是算法的版本标识。 10$：这是成本（或难度）参数，表示加密的计算复杂度。
        // B4WcenV2GOOpIPVG/sQSWu：这是盐值。在您的例子中，盐值是B4WcenV2GOOpIPVG/sQSWu。
        // Z04llGaDdQSOvzXfJtpMYOKZHpJNLx.：这是实际的散列值，即原始密码加上盐值经过BCrypt算法处理后的结果。
        String sigPwd = PasswordEncoderUtils.enc(userLoginByPsdDTO.getPassword());
        // "123456" 是前端传回来的密码
        boolean matches = PasswordEncoderUtils.decrypt("123456", sigPwd);

        if (matches) {
            // 准备jwt的数据
            Map<String, Object> map = new HashMap<>();
            map.put("id", currentPrincipal.getId());
            map.put("username", currentPrincipal.getUsername());

            // 生产token并返回
            return JsonResult.ok(JwtUtils.getToken(map));
        }

        return JsonResult.fail(ServiceCode.ERR_USERNAME_PASSWORD, "用户名或密码错误");

    }
}
