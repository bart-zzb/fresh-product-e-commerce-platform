package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.tb_address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    String contactPhone;

    String account;

    String district;

    String city;

    String province;

    String addressDetail;

    Long tbUserId;

    String addressName;

    Integer isDefault;

    String receiver;
}