package cn.tedu.mall.service.pojo.bo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressBO implements Serializable {
    Long id;

    String contactPhone;

    String account;

    String district;

    String city;

    String province;

    String addressDetail;

    Long tbUserId;

    String addressName;
}
