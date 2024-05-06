package cn.tedu.mall.service.pojo.bo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BannerIndexBO implements Serializable {
    Long id;

    String imgUrl;

    String name;

    Integer sort;
}
