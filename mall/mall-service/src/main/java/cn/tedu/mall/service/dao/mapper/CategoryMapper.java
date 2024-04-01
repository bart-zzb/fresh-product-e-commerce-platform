package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.CategoryPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<CategoryPO> {
    List<CategoryPO> selectSortedCategoryByParentId(Long parentId, Integer pageNum, Integer pageSize);
}
