package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
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
        return PojoConvert.convertList(categoryPOList, CategoryVO.class);
    }

    /**
     * 增加商品类别
     * @param categoryAddDTO 商品类别
     */
    @Override
    public void addCategory(CategoryAddDTO categoryAddDTO) {
        Long countByCategoryName = categoryRepository.getCountByCategoryName(categoryAddDTO.getCategoryName());
        if (countByCategoryName >= 1L){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,"商品类别已存在！");
        }else{
            CategoryPO categoryPO = PojoConvert.convert(categoryAddDTO, CategoryPO.class);
            categoryPO.setIsParent(0);
            if(categoryAddDTO.getParentId()==0L){
                categoryPO.setLevel(1);
            }else{
                CategoryPO categoryParentPO = categoryRepository.getCategoryById(categoryAddDTO.getParentId());
                if (categoryParentPO == null){
                    throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,"商品父类不存在！");
                }
                if(categoryParentPO.getEnable()==0){
                    throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,"商品父类已禁用！");
                }

                if(categoryParentPO.getIsParent() != 1){
                    categoryParentPO.setIsParent(1);
                    categoryRepository.updateCategoryByCategoryPO(categoryParentPO);
                }
                categoryPO.setLevel(categoryParentPO.getLevel()+1);
            }
            categoryPO.setSort(1);
            categoryRepository.saveCategory(categoryPO);
        }
    }
}
