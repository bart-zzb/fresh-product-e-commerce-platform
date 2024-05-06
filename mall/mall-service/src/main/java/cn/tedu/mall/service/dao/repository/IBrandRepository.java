package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.BrandBO;
import cn.tedu.mall.service.pojo.po.BrandPO;
import cn.tedu.mall.service.pojo.vo.BrandVO;

import java.util.List;

public interface IBrandRepository {
    List<BrandBO> listForAll();

    BrandPO selectBrandById(Long id);
}
