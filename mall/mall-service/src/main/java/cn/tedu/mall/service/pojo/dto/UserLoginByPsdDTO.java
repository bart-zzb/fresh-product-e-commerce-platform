package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginByPsdDTO {

    @NotBlank(message = " 用户名不能为空")
    @ApiModelProperty(value = "用户名",required = true,dataType = "String")
    String username;

    @NotBlank(message = " 密码不能为空")
    @ApiModelProperty(value = "密码",required = true,dataType = "String")
    String password;
}
