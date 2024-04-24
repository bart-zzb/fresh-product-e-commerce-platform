package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.dto.CategoryUpdateDTO;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface ICategoryService {
    void initCategory();

    List<CategoryVO> listCategoryByLevel(Integer level);

    void addCategory(CategoryAddDTO categoryAddDTO);

    void deleteCategoryById(Long id);

    CategoryPO getCategoryById(Long id);

    void updateCategory(CategoryUpdateDTO categoryUpdateDTO);

    List<CategoryTreeVO> treeCategory();
}
