package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IBannerRepository;
import cn.tedu.mall.service.pojo.bo.BannerIndexBO;
import cn.tedu.mall.service.pojo.vo.BannerIndexVO;
import cn.tedu.mall.service.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private IBannerRepository bannerRepository;

    @Override
    public List<BannerIndexBO> listForIndex() {
        return bannerRepository.listForIndex();
    }
}
