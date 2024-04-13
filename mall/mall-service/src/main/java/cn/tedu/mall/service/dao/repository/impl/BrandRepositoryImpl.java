package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.BrandMapper;
import cn.tedu.mall.service.dao.repository.IBrandRepository;
import cn.tedu.mall.service.pojo.po.BrandPO;
import cn.tedu.mall.service.pojo.vo.BrandVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class BrandRepositoryImpl implements IBrandRepository {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandVO> listForAll() {
        return brandMapper.selectListForAll();
    }

    @Override
    public BrandPO selectBrandById(Long id) {
        return brandMapper.selectById(id);
    }
}
