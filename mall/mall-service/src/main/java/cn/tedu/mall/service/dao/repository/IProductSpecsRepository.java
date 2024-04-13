package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;

public interface IProductSpecsRepository {
    ProductSpecsVO getProductSpecsById(Long id);
}
