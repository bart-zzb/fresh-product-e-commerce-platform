package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.vo.BannerIndexVO;

import java.util.List;

public interface IBannerRepository {
    List<BannerIndexVO> listForIndex();
}
