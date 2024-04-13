package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IProductSpecsService {
    ProductSpecsVO getProductSpecsById(Long id);
}
