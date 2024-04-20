package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsTreeVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IProductSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class ProductSpecsServiceImpl implements IProductSpecsService {
    @Autowired
    private IProductSpecsRepository productSpecsRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ProductSpecsVO getProductSpecsById(Long id) {
        return productSpecsRepository.getProductSpecsById(id);
    }

    @Override
    public PageData<ProductSpecsVO> getProductSpecsByCategoryId(Long id, Integer pageNum, Integer pageSize) {
        return productSpecsRepository.getProductSpecsByCategoryId(id, pageNum, pageSize);
    }

    @Override
    public List<ProductSpecsTreeVO> getProductSpecsTree() {
        List<CategoryPO> topCategoryPO = categoryRepository.getCategoryListByParentId(0L);
        Map<String, String> map = new HashMap<>();
        map.put("categoryName","text");
        List<ProductSpecsTreeVO> topCategoryTreeVOS = PojoConvert.convertList(topCategoryPO, ProductSpecsTreeVO.class, map);
        List<CategoryPO> all = categoryRepository.getAll();

        for (ProductSpecsTreeVO topVo : topCategoryTreeVOS) {
            appendChild(topVo, all);
        }
        return topCategoryTreeVOS;
    }

    //递归方法
    private ProductSpecsTreeVO appendChild(ProductSpecsTreeVO vo, List<CategoryPO> all) {
        List<ProductSpecsTreeVO> children = new ArrayList<>();
        for (CategoryPO categoryPO : all) {
            if (vo.getId().equals(categoryPO.getParentId())) {
                ProductSpecsTreeVO childVO = PojoConvert.convert(categoryPO, ProductSpecsTreeVO.class);
                assert childVO != null;
                childVO.setText(categoryPO.getCategoryName());
                children.add(appendChild(childVO, all)); //去找childVo的子级
            } else {
                //没有子级了,终止条件 不调用appendChild,退出了
            }
        }
        vo.setChildren(children);
        vo.setProductSpecsList(getProductSpecsByCategoryId(vo.getId(),1,Integer.MAX_VALUE).getList());
        return vo;
    }
}
