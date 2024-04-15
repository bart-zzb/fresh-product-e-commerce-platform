package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IProductSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class ProductSpecsServiceImpl implements IProductSpecsService {
    @Autowired
    private IProductSpecsRepository productSpecsRepository;

    @Override
    public ProductSpecsVO getProductSpecsById(Long id) {
        return productSpecsRepository.getProductSpecsById(id);
    }

    @Override
    public PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize) {
        return productSpecsRepository.getProductSpecsByCategoryId(id, pageNum, pageSize);
    }
}
