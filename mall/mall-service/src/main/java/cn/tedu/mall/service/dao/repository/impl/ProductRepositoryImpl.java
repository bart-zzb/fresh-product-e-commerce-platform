package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.ProductMapper;
import cn.tedu.mall.service.dao.repository.IProductRepository;
import cn.tedu.mall.service.pojo.po.ProductPO;
import cn.tedu.mall.service.pojo.vo.ProductVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class ProductRepositoryImpl implements IProductRepository {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductPO selectProductByProductName(String productName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("product_name",productName);
        return productMapper.selectOne(queryWrapper);
    }

    @Override
    public int saveProduct(ProductPO productPO) {
        return productMapper.insert(productPO);
    }

    @Override
    public int deleteProductById(Long id) {
        return productMapper.deleteById(id);
    }

    @Override
    public ProductVO getProductById(Long id) {
        return productMapper.selectProductById(id);
    }
}
