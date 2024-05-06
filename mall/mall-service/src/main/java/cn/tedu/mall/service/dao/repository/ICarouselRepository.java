package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.CarouselIndexBO;

import java.util.List;

public interface ICarouselRepository {
    List<CarouselIndexBO> listForIndex();
}
