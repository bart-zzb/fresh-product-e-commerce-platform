package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;

import java.util.List;

public interface ICategoryCacheRepository {
    void saveTreeCategory(List<CategoryTreeVO> CategoryTreeVOS);
}
