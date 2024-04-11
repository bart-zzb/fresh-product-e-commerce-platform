package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICategoryCacheRepository;
import cn.tedu.mall.service.dao.repository.ICategoryRepository;
import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.dto.CategoryUpdateDTO;
import cn.tedu.mall.service.pojo.po.CategoryPO;
import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import cn.tedu.mall.service.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Primary
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ICategoryCacheRepository categoryCacheRepository;

    /**
     * 初始化类别数据到redis
     */
    @Override
    public void initCategory() {
        // 1 从数据库查出来
        List<CategoryTreeVO> CategoryTreeVOS = treeCategory();
        // 2 放到redis
        categoryCacheRepository.saveTreeCategory(CategoryTreeVOS);
    }

    @Override
    public List<CategoryVO> listCategoryByLevel(Integer level) {
        List<CategoryPO> categoryPOList = categoryRepository.getCategoryListByLevel(level);
        return PojoConvert.convertList(categoryPOList, CategoryVO.class);
    }

    /**
     * 增加商品类别
     * @param categoryAddDTO 商品类别
     */
    @Override
    public void addCategory(CategoryAddDTO categoryAddDTO) {
        Long countByCategoryName = categoryRepository.getCountByCategoryName(categoryAddDTO.getCategoryName());
        if (countByCategoryName >= 1L) {
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CATEGORY_ALREADY_EXISTED);
        } else {
            CategoryPO categoryPO = PojoConvert.convert(categoryAddDTO, CategoryPO.class);
            categoryPO.setIsParent(0);
            if (categoryAddDTO.getParentId() == 0L) {
                categoryPO.setLevel(1);
            } else {
                CategoryPO categoryParentPO = categoryRepository.getCategoryById(categoryAddDTO.getParentId());
                if (categoryParentPO == null) {
                    throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CATEGORY_PARENT_NOT_EXIST);
                }
                if (categoryParentPO.getEnable() == 0) {
                    throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CATEGORY_PARENT_NOT_ENABLE);
                }

                if (categoryParentPO.getIsParent() != 1) {
                    categoryParentPO.setIsParent(1);
                    categoryRepository.updateCategoryByCategoryPO(categoryParentPO);
                }
                categoryPO.setLevel(categoryParentPO.getLevel() + 1);
            }
            List<CategoryPO> lastCategoryPOList = categoryRepository.getSortedCategoryByParentId(categoryPO.getParentId(), 0, 1);

            //判断sort是多少
            if (!lastCategoryPOList.isEmpty()) {
                log.debug("lastCategoryPO" + lastCategoryPOList.get(0));
                categoryPO.setSort(lastCategoryPOList.get(0).getSort() + 1);
            } else {
                categoryPO.setSort(1);
            }
            categoryRepository.saveCategory(categoryPO);
        }
        //全量更新
        updateCategoryTreeForRedis();
    }

    @Override
    public void deleteCategoryById(Long id) {
        CategoryPO categoryPO = categoryRepository.getCategoryById(id);
        if(categoryPO == null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CATEGORY_NOT_EXIST);
        }
        List<CategoryPO> categoryListByParentId = categoryRepository.getCategoryListByParentId(id);
        StringBuffer errorMessage = new StringBuffer(ServiceConstant.CATEGORY_CHILDREN_IS_EXISTED);
        if (!categoryListByParentId.isEmpty()) {
            for (int i = 0; i < categoryListByParentId.size(); i++) {
                errorMessage.append(": [ "+ "id: "+categoryListByParentId.get(i).getId() + ", categoryName: " + categoryListByParentId.get(i).getCategoryName() + " ] ");
            }
            throw new ServiceException(ServiceCode.ERROR_DELETE, errorMessage.toString());
        } else {
            categoryRepository.deleteCategoryById(id);
        }
        //全量更新
        updateCategoryTreeForRedis();
    }

    @Override
    public CategoryPO getCategoryById(Long id){
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public void updateCategory(CategoryUpdateDTO categoryUpdateDTO) {
       CategoryPO origCategoryPO = categoryRepository.getCategoryById(categoryUpdateDTO.getId());
        if(origCategoryPO==null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,ServiceConstant.CATEGORY_NOT_EXIST);
        }
        CategoryPO categoryPO = PojoConvert.convert(categoryUpdateDTO, CategoryPO.class);

        categoryRepository.updateCategoryByCategoryPO(categoryPO);
        //全量更新
        updateCategoryTreeForRedis();
    }

    @Override
    public List<CategoryTreeVO> treeCategory() {
        List<CategoryPO> topCategoryPO = categoryRepository.getCategoryListByParentId(0L);
        List<CategoryTreeVO> topCategoryTreeVOS = PojoConvert.convertList(topCategoryPO, CategoryTreeVO.class);
        List<CategoryPO> all = categoryRepository.getAll();

        for (CategoryTreeVO topVo : topCategoryTreeVOS) {
            appendChild(topVo, all);
        }
        return topCategoryTreeVOS;
    }

    //递归方法
    private CategoryTreeVO appendChild(CategoryTreeVO vo, List<CategoryPO> all) {
        List<CategoryTreeVO> children = new ArrayList<>();
        for (CategoryPO categoryPO : all) {
            if (vo.getId().equals(categoryPO.getParentId())) {
                CategoryTreeVO childVO = PojoConvert.convert(categoryPO, CategoryTreeVO.class);
                children.add(appendChild(childVO, all)); //去找childVo的子级
            } else {
                //没有子级了,终止条件 不调用appendChild,退出了
            }
        }
        vo.setChildren(children);
        return vo;
    }

    //全量更新
    private void updateCategoryTreeForRedis() {
        List<CategoryTreeVO> categoryTreeVOS = treeCategory();
        categoryCacheRepository.saveTreeCategory(categoryTreeVOS);
    }

}
