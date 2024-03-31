package cn.tedu.mall.passport.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.passport.pojo.dto.UserLoginInfoDTO;
import cn.tedu.mall.passport.pojo.vo.UserLoginResultVO;
import cn.tedu.mall.passport.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/passport")
@Validated
@Api(tags = "用户服务")
public class UserController {
    @Value("${file-path}")
    private String dirPath;

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonResult login(@Validated UserLoginInfoDTO userLoginInfoDTO,
                            @ApiIgnore HttpServletRequest request) {
        UserLoginResultVO vo = userService.login(userLoginInfoDTO);
        return JsonResult.ok(vo);
    }


}