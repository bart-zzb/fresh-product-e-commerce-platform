package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartUpdateDTO implements Serializable {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotNull(message = "是否选中不能为空")
    @Range(min = 0, max = 1, message = "只能为0或者1")
    @ApiModelProperty(value = "是否选中",required = true,dataType = "Integer")
    Integer tbProductChecked;

    @NotBlank(message = " 数量不能为空")
    @Pattern(regexp = "^\\d+$", message = "请输入正整数或零")
    @ApiModelProperty(value = "数量",required = true,dataType = "Integer")
    Integer amount;
}
