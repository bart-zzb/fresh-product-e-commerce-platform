package cn.tedu.mall.common.pojo.po;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserStatePO implements Serializable {
    Long userId;
    String auth;
    Integer enable;
}
