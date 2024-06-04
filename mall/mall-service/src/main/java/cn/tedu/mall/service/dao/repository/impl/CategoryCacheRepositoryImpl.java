package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.repository.ICategoryCacheRepository;
import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class CategoryCacheRepositoryImpl implements ICategoryCacheRepository {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveTreeCategory(List<CategoryTreeVO> CategoryTreeVOS) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("CATEGORY_ALL", CategoryTreeVOS);
    }
}
