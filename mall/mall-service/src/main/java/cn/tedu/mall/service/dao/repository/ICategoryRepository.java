package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.dto.CategoryUpdateDTO;
import cn.tedu.mall.service.pojo.po.CategoryPO;

import java.util.List;

public interface ICategoryRepository {
    int saveCategory(CategoryPO categoryPO);

    int deleteCategory(CategoryPO categoryPO);

    int deleteCategoryById(Long id);

    Long getCountByCategoryName(String categoryName);

    List<CategoryPO> getCategoryListByLevel(Integer level);

    CategoryPO getCategoryById(Long id);

    List<CategoryPO> getCategoryListByParentId(Integer parentId);

    int updateCategoryByCategoryUpdateDTO(CategoryUpdateDTO categoryUpdateDTO);

    int updateCategoryByCategoryPO(CategoryPO categoryPO);
}
