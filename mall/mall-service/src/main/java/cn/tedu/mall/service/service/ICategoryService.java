package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ICategoryService {
    List<CategoryVO> listCategoryByLevel(Integer level);

    @Transactional
    void addCategory(CategoryAddDTO categoryAddDTO);
}
