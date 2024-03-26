package cn.tedu.mall.passport.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserLoginInfoDTO implements Serializable {
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,14}$", message = "用户名必须由4~15长度的字母、数组、下划线组成，且第1个字符必须是字母")
    @ApiModelProperty(value = "用户名", required = true, example = "root")
    private String username;

    /**
     * 密码（原文）
     */
    @NotNull(message = "请提交用户名")
    @Pattern(regexp = "^.{4,15}$", message = "密码必须是4~15长度的字符组成")
    @ApiModelProperty(value = "密码", required = true, example = "123456")
    private String password;
}
