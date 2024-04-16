package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecsTreeVO implements Serializable {
    Long id;

    String text;

    List <ProductSpecsTreeVO> children;

    List <ProductSpecsVO> productSpecsList;
}
