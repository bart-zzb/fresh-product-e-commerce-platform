package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.dto.CategoryUpdateDTO;
import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICategoryService {
    void initCategory();

    List<CategoryVO> listCategoryByLevel(Integer level);

    @Transactional
    void addCategory(CategoryAddDTO categoryAddDTO);

    @Transactional
    void deleteCategoryById(Long id);

    @Transactional
    void updateCategory(CategoryUpdateDTO categoryUpdateDTO);

    List<CategoryTreeVO> treeCategory();
}
