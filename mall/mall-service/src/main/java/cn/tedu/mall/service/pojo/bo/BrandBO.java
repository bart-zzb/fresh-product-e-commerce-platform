package cn.tedu.mall.service.pojo.bo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandBO implements Serializable {
    Long id;

    String name;

    Integer status;

    String info;
}
