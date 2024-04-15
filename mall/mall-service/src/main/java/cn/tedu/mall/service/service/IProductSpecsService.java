package cn.tedu.mall.service.service;

import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IProductSpecsService {
    ProductSpecsVO getProductSpecsById(Long id);

    PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize);
}
