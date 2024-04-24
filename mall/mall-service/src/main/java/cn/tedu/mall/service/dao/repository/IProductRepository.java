package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.ProductPO;
import cn.tedu.mall.service.pojo.vo.ProductVO;

public interface IProductRepository{
    ProductPO selectProductByProductName(String productName);

    int saveProduct(ProductPO productPO);

    int deleteProductById(Long id);

    ProductVO getProductById(Long id);

    void modifyProductSales(Long productId, Integer amount);
}
