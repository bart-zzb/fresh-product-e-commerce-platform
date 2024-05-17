package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.pojo.bo.ProductSpecsBO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;

public interface IProductSpecsRepository {
    ProductSpecsVO getProductSpecsById(Long id);

    PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize);

    void deleteProductSpecsAmountByIdAndAmount(Long tbProductSpecId, Integer amount);

    void returnProductSpecsAmountByIdAndAmount(Long tbProductSpecId, Integer amount);

    ProductSpecsBO getProductIdByProductSpecsId(Long tbProductSpecId);
}
