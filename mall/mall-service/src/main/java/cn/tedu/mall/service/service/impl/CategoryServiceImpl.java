package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import cn.tedu.mall.service.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryVO> listCategoryByLevel(Integer level) {
        List<CategoryPO> categoryPOList = categoryRepository.getCategoryListByLevel(level);
        List<CategoryVO> categoryVOList = PojoConvert.convertList(categoryPOList, CategoryVO.class);
        return categoryVOList;
    }

    /**
     * 增加类别
     * 1 名称不能重复 重复报错
     * 2 类别的级别是 子级还是顶级
     *     顶级的 parent_id == 0  子级 parent_id不为0
     * 3 如果是子级 需要判断父级是否存在
     * 4 如果是父级 直接保存  山西直接保存
     * 5 判断父级是否禁用  可选
     * 6 计算深度
     *    父级 parentId=0  深度 = 1
     *    子级   深度 = 父级的深度 + 1
     * 7 计算isParent
     *     如果是父级 isParent = 0  山西 = 0
     *     如果是子级 把父级的isParent = 1    isParent = 1
     * @param categoryAddDTO
     */
    @Override
    public void addCategory(CategoryAddDTO categoryAddDTO) {

    }
}
