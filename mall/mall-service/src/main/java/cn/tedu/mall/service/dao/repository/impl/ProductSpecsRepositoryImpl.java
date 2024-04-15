package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PageInfoToPageDataConverter;
import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.dao.mapper.ProductSpecsMapper;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
