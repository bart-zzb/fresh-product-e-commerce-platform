package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.CategoryVO;

import java.util.List;

public interface ICategoryService {
    List<CategoryVO> listCategoryByLevel(Integer level);
}
