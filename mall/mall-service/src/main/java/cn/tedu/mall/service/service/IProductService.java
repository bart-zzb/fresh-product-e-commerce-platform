package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.ProductAddDTO;
import cn.tedu.mall.service.pojo.vo.ProductVO;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface IProductService {
    void addProduct(ProductAddDTO productAddDTO);

    void deleteProductById(Long id);

    ProductVO getProductById(Long id);
}
