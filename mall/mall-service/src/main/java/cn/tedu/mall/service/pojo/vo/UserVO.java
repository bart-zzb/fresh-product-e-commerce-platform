package cn.tedu.mall.service.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVO implements Serializable {
    Long id;

    Integer userType;

    String profileImg;

    String nickname;

    String district;

    String city;

    String province;

    String addressDetail;

    String username;

    String contactPhone;

    Integer userPoint;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal userBalance;

    Integer couponCount;

    String token;
}
