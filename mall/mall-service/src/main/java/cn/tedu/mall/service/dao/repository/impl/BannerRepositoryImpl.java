package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.BannerMapper;
import cn.tedu.mall.service.dao.repository.IBannerRepository;
import cn.tedu.mall.service.pojo.bo.BannerIndexBO;
import cn.tedu.mall.service.pojo.po.BannerPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Primary
@Repository
public class BannerRepositoryImpl implements IBannerRepository {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerIndexBO> listForIndex() {
        List<BannerPO> bannerPOS = bannerMapper.selectListForIndex();
        List<BannerIndexBO> bannerIndexBOS = PojoConvert.convertList(bannerPOS, BannerIndexBO.class);
        log.debug("出参{}", bannerIndexBOS);
        return bannerIndexBOS;
    }
}
