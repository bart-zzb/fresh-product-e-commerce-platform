package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressDTO implements Serializable {
    Long id;

    @NotBlank(message = " 手机号不能为空")
    @ApiModelProperty(value = "手机号",required = true,dataType = "String")
    String contactPhone;

    @NotBlank(message = " 区不能为空")
    @ApiModelProperty(value = "区",required = true,dataType = "String")
    String district;

    @NotBlank(message = " 市不能为空")
    @ApiModelProperty(value = "市",required = true,dataType = "String")
    String city;

    @NotBlank(message = " 省不能为空")
    @ApiModelProperty(value = "省",required = true,dataType = "String")
    String province;

    @NotBlank(message = " 详细地址不能为空")
    @ApiModelProperty(value = "详细地址",required = true,dataType = "String")
    String addressName;

    @NotBlank(message = " 收货人不能为空")
    @ApiModelProperty(value = "收货人",required = true,dataType = "String")
    String receiver;
}
