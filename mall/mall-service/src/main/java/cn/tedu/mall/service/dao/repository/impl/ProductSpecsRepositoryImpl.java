package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PageInfoToPageDataConverter;
import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.dao.mapper.ProductSpecsMapper;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.bo.ProductSpecsBO;
import cn.tedu.mall.service.pojo.po.ProductSpecsPO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Primary
@Repository
public class ProductSpecsRepositoryImpl implements IProductSpecsRepository {
    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Override
    public ProductSpecsVO getProductSpecsById(Long id) {
        return productSpecsMapper.selectProductSpecsById(id);
    }

    @Override
    public PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductSpecsVO> list = productSpecsMapper.selectProductSpecsByCategoryId(id);
        PageInfo<ProductSpecsVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public void deleteProductSpecsAmountByIdAndAmount(Long tbProductSpecId, Integer amount) {
        log.debug("tbProductSpecId" + tbProductSpecId);
        ProductSpecsPO productSpecsPO = productSpecsMapper.selectById(tbProductSpecId);
        log.debug("当前productSpecsPO" + productSpecsPO);
        if (productSpecsPO.getAmount() < amount) {
            throw new ServiceException(ServiceCode.ERROR_STOCK_NO_ENOUGH, ServiceConstant.PRODUCT_STOCK_NO_ENOUGH);
        }
        productSpecsPO.setSales(productSpecsPO.getSales() + amount);
        productSpecsPO.setAmount(productSpecsPO.getAmount() - amount);
        log.debug("修改后productSpecsPO" + productSpecsPO);
        productSpecsMapper.updateById(productSpecsPO);
    }

    @Override
    public ProductSpecsBO getProductIdByProductSpecsId(Long tbProductSpecId) {
        return productSpecsMapper.selectProductIdByProductSpecsId(tbProductSpecId);
    }
}
