package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import cn.tedu.mall.service.pojo.dto.UserAddressDTO;
import cn.tedu.mall.service.pojo.vo.UserAddressVO;
import cn.tedu.mall.service.service.IUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public JsonResult getAllCurrentUserAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        List<UserAddressBO> userAddressBOS = userAddressService.getUserAddressByUserId(currentPrincipal.getId());
        List<UserAddressVO> userAddressVOS = PojoConvert.convertList(userAddressBOS, UserAddressVO.class);
        return JsonResult.ok(userAddressVOS);
    }

    @ApiOperation("更新当前用户信息地址默认状态")
    @PostMapping("/change")
    public JsonResult changeUserAddressIsDefault(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, Long id) {
        userAddressService.changeUserAddressIsDefaultById(currentPrincipal.getId(), id);
        return JsonResult.ok();
    }

    @ApiOperation("获取当前用户信息默认地址表")
    @GetMapping("/get/defaultAddress")
    public JsonResult getDefaultAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        UserAddressBO userAddressBO = userAddressService.getDefaultAddressByUserId(currentPrincipal.getId());
        if (userAddressBO == null) {
            return JsonResult.ok(null);
        } else {
            UserAddressVO userAddressVO = PojoConvert.convert(userAddressBO, UserAddressVO.class);
            return JsonResult.ok(userAddressVO);
        }
    }

    @ApiOperation("根据当前AddressId获取当前地址详细信息")
    @GetMapping("/get/current")
    public JsonResult getAddressById(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, Long id) {
        UserAddressBO userAddressBO = userAddressService.getAddressById(currentPrincipal.getId(), id);
        if (userAddressBO == null) {
            return JsonResult.ok(null);
        } else {
            UserAddressVO userAddressVO = PojoConvert.convert(userAddressBO, UserAddressVO.class);
            return JsonResult.ok(userAddressVO);
        }
    }

    @ApiOperation("更新当前用户的地址信息")
    @PostMapping("/update")
    public JsonResult updateAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated UserAddressDTO userAddressDTO) {
        if (userAddressDTO == null) {
            return JsonResult.fail(ServiceCode.ERROR_ADDRESS_UPDATE_FAILED, ServiceConstant.ADDRESS_UPDATE_FAILED);
        }
        UserAddressBO userAddressBO = PojoConvert.convert(userAddressDTO, UserAddressBO.class);
        userAddressBO.setAddressDetail(userAddressBO.getProvince() + userAddressBO.getCity() + userAddressBO.getDistrict() + userAddressBO.getAddressName());
        userAddressBO.setAccount("xxxx");
        userAddressBO.setTbUserId(currentPrincipal.getId());
        userAddressService.updateAddress(userAddressBO);
        return JsonResult.ok();
    }

    @ApiOperation("新增当前用户的地址信息")
    @PostMapping("/add")
    public JsonResult addAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated UserAddressDTO userAddressDTO) {
        if (userAddressDTO == null) {
            return JsonResult.fail(ServiceCode.ERROR_ADDRESS_INSERT_FAILED, ServiceConstant.ADDRESS_INSERT_FAILED);
        }
        UserAddressBO userAddressBO = PojoConvert.convert(userAddressDTO, UserAddressBO.class);
        userAddressBO.setAddressDetail(userAddressBO.getProvince() + userAddressBO.getCity() + userAddressBO.getDistrict() + userAddressBO.getAddressName());
        userAddressBO.setAccount("xxxx");
        userAddressBO.setTbUserId(currentPrincipal.getId());
        userAddressService.addAddress(userAddressBO);
        return JsonResult.ok();
    }

    @ApiOperation("新增当前用户的地址信息")
    @PostMapping("/delete")
    public JsonResult deleteAddress(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, Long id) {
        userAddressService.deleteAddressById(id);
        return JsonResult.ok();
    }

}
