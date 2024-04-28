package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.JwtUtils;
import cn.tedu.mall.common.util.PasswordEncoderUtils;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.UserLoginByPsdDTO;
import cn.tedu.mall.service.pojo.dto.UserRegByUserPwdDTO;
import cn.tedu.mall.service.pojo.bo.UserBO;
import cn.tedu.mall.service.pojo.vo.UserVO;
import cn.tedu.mall.service.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("根据用户名和密码登录")
    @PostMapping("/loginByUsernameAndPassword")
    public JsonResult loginByUserNameAndPassword(@Validated UserLoginByPsdDTO userLoginByPsdDTO) {
        log.debug(userLoginByPsdDTO.getUsername() + userLoginByPsdDTO.getPassword());

        UserBO userBO = userService.getUserByUsername(userLoginByPsdDTO.getUsername());
        if(userBO ==null){
            throw new ServiceException(ServiceCode.ERR_USERNAME_PASSWORD, ServiceConstant.ERROR_USERNAME_PASSWORD);
        }

        return userVOSetToken(userLoginByPsdDTO.getUsername(), userLoginByPsdDTO.getPassword(), userBO);
    }

    @ApiOperation("注册并登录")
    @PostMapping("/regAndLogin")
    public JsonResult regAndLogin(@Validated UserRegByUserPwdDTO userRegByUserPwdDTO){
        log.debug("注册用户入参{}", userRegByUserPwdDTO);
        UserBO userBO = userService.getUserByUsername(userRegByUserPwdDTO.getUsername());
        if(userBO !=null){
            throw new ServiceException(ServiceCode.ERR_USERNAME_ALREADY_EXIST, ServiceConstant.ERROR_USERNAME_ALREADY_EXIST);
        }
        //数据库存储加盐加密后的密码
        userService.saveUserByUsernameAndPassword(userRegByUserPwdDTO.getUsername(), PasswordEncoderUtils.enc(userRegByUserPwdDTO.getPassword()));
        UserBO currentUserBO = userService.getUserByUsername(userRegByUserPwdDTO.getUsername());
        return userVOSetToken(userRegByUserPwdDTO.getUsername(), userRegByUserPwdDTO.getPassword(), currentUserBO);
    }

    /**
     *
     * @param username 前端的username
     * @param password 前端的password
     */
    private JsonResult userVOSetToken(String username, String password, UserBO userBO){
        boolean matches = PasswordEncoderUtils.decrypt(password, userBO.getPassword());
        UserVO userVO = PojoConvert.convert(userBO, UserVO.class);
        if (matches) {
            // 准备jwt的数据
            Map<String, Object> map = new HashMap<>();
            map.put("id", userBO.getId());
            map.put("username", userBO.getUsername());
            // 生产token并返回
            userVO.setToken(JwtUtils.getToken(map));
        }
        if (userVO.getToken()==null){
            return JsonResult.fail(ServiceCode.ERR_USERNAME_PASSWORD, ServiceConstant.ERROR_USERNAME_PASSWORD);
        }else{
            return JsonResult.ok(userVO);
        }
    }
}
