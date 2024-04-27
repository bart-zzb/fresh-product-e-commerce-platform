package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import cn.tedu.mall.service.pojo.vo.UserAddressVO;
import cn.tedu.mall.service.service.IUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "用户地址模块")
@Slf4j
@Validated
@RequestMapping("/admin/userAddress")
@RestController
public class UserAddressController {
    @Autowired
    private IUserAddressService userAddressService;

    @ApiOperation("获取当前用户所有地址信息")
    @GetMapping("/get/all")
    public JsonResult getAllCurrentUserAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal){
        List<UserAddressBO> userAddressBOS = userAddressService.getUserAddressByUserId(currentPrincipal.getId());
        List<UserAddressVO> userAddressVOS = PojoConvert.convertList(userAddressBOS, UserAddressVO.class);
        for (UserAddressVO userAddressVO : userAddressVOS) {
            userAddressVO.setText(userAddressVO.getDistrict()+userAddressVO.getCity()+userAddressVO.getProvince()+userAddressVO.getAddressDetail());
        }
        return JsonResult.ok(userAddressVOS);
    }
}
