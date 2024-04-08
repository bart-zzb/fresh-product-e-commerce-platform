package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.CategoryMapper;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;

import cn.tedu.mall.service.pojo.po.CategoryPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int saveCategory(CategoryPO categoryPO){
        return categoryMapper.insert(categoryPO);
    }

    @Override
    public int deleteCategory(CategoryPO categoryPO){
        return categoryMapper.deleteById(categoryPO);
    }

    @Override
    public int deleteCategoryById(Long id){
        return categoryMapper.deleteById(id);
    }

    @Override
    public CategoryPO getCategoryById(Long id){
        return categoryMapper.selectById(id);
    }

    @Override
    public Long getCountByCategoryName(String categoryName){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_name",categoryName);
        return categoryMapper.selectCount(queryWrapper);
    }

    @Override
    public List<CategoryPO> getCategoryListByLevel(Integer level) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("level",level);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public List<CategoryPO> getCategoryListByParentId(Long parentId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",parentId);
        return categoryMapper.selectList(queryWrapper);
    }

    @Override
    public int updateCategoryByCategoryPO(CategoryPO categoryPO){
         return categoryMapper.updateById(categoryPO);
    }

    @Override
    public List<CategoryPO> getSortedCategoryByParentId(Long parentId, Integer pageNum, Integer pageSize){
        return categoryMapper.selectSortedCategoryByParentId(parentId, pageNum, pageSize);
    }

    @Override
    public List<CategoryPO> getAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        return categoryMapper.selectList(queryWrapper);
    }
}
