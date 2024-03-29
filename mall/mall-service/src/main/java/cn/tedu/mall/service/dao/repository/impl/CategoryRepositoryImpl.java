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
    public List<CategoryPO> getCategoryListByLevel(Integer level) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("level",level);
        return categoryMapper.selectList(queryWrapper);
    }
}
