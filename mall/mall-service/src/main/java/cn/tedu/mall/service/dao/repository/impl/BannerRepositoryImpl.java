package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.BannerMapper;
import cn.tedu.mall.service.dao.repository.IBannerRepository;
import cn.tedu.mall.service.pojo.vo.BannerIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class BannerRepositoryImpl implements IBannerRepository {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerIndexVO> listForIndex() {
        return bannerMapper.selectListForIndex();
    }
}
