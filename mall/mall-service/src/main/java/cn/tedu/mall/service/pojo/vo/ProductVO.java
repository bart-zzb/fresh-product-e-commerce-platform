package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVO implements Serializable {
    Long id;

    String productName;

    Json attributeList;

    Long tbCategoryId;

    Long tbBrandId;

    Integer sales;

    Integer status;

    String info;
}
