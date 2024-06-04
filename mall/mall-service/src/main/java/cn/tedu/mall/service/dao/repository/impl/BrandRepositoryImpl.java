package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.BrandMapper;
import cn.tedu.mall.service.dao.repository.IBrandRepository;
import cn.tedu.mall.service.pojo.bo.BrandBO;
import cn.tedu.mall.service.pojo.po.BrandPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Primary
@Repository
public class BrandRepositoryImpl implements IBrandRepository {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandBO> listForAll() {
        List<BrandPO> brandPOS = brandMapper.selectListForAll();
        List<BrandBO> brandBOS = PojoConvert.convertList(brandPOS, BrandBO.class);
        log.debug("出参{}", brandBOS);
        return brandBOS;
    }

    @Override
    public BrandPO selectBrandById(Long id) {
        return brandMapper.selectById(id);
    }
}
