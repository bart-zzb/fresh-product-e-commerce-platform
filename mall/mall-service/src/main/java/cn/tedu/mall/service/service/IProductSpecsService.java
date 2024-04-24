package cn.tedu.mall.service.service;

import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.pojo.dto.ProductSpecDeleteDTO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsTreeVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IProductSpecsService {
    ProductSpecsVO getProductSpecsById(Long id);

    PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize);

    List<ProductSpecsTreeVO> getProductSpecsTree();

    void deleteProductSpecsAmount(Long id, List<ProductSpecDeleteDTO> productSpecDeleteDTOS);
}
