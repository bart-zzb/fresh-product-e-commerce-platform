package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.CarouselMapper;
import cn.tedu.mall.service.dao.repository.ICarouselRepository;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class CarouselRepositoryImpl implements ICarouselRepository {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<CarouselIndexVO> listForIndex() {
        return carouselMapper.selectListForIndex();
    }
}
