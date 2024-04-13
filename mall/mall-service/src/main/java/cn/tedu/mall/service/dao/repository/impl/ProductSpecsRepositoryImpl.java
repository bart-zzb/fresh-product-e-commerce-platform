package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.ProductSpecsMapper;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class ProductSpecsRepositoryImpl implements IProductSpecsRepository {
    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Override
    public ProductSpecsVO getProductSpecsById(Long id) {
        return productSpecsMapper.selectProductSpecsById(id);
    }
}
