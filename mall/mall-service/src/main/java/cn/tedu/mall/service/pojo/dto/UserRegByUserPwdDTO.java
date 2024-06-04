package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegByUserPwdDTO {

    @NotBlank(message = " 用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, dataType = "String")
    String username;

    @NotBlank(message = " 密码不能为空")
    @Size(min = 5, max = 12, message = "密码必须在5-12位")
    @ApiModelProperty(value = "密码", required = true, dataType = "String")
    String password;
}
