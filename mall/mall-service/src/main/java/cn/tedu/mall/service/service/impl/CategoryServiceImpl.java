package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import cn.tedu.mall.service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryVO> listCategoryByLevel(Integer level) {
        List<CategoryPO> categoryPOList = categoryRepository.getCategoryListByLevel(level);
        List<CategoryVO> categoryVOList = PojoConvert.convertList(categoryPOList, CategoryVO.class);
        return categoryVOList;
    }
}
