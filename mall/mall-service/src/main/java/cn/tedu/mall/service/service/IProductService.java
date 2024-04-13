package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.ProductAddDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IProductService {
    void addProduct(ProductAddDTO productAddDTO);
}
