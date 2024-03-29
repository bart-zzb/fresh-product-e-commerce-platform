package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.CategoryPO;

import java.util.List;

public interface ICategoryRepository {
    List<CategoryPO> getCategoryListByLevel(Integer level);
}
