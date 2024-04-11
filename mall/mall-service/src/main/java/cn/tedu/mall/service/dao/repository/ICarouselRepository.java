package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;

import java.util.List;

public interface ICarouselRepository {
    List<CarouselIndexVO> listForIndex();
}
