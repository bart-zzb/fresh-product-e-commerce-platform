package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.ProductPO;

public interface IProductRepository{
    ProductPO selectProductByProductName(String productName);

    int saveProduct(ProductPO productPO);
}
