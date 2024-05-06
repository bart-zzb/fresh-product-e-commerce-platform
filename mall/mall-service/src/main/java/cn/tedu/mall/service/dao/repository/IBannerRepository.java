package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.BannerIndexBO;

import java.util.List;

public interface IBannerRepository {
    List<BannerIndexBO> listForIndex();
}
