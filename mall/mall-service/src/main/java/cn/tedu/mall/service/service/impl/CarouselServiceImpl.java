package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.ICarouselRepository;
import cn.tedu.mall.service.pojo.bo.CarouselIndexBO;
import cn.tedu.mall.service.service.ICarouselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class CarouselServiceImpl implements ICarouselService {
    @Autowired
    private ICarouselRepository carouselRepository;

    @Override
    public List<CarouselIndexBO> listForIndex() {
        List<CarouselIndexBO> carouselIndexBOS = carouselRepository.listForIndex();
        log.debug("查询查询轮播图功能,业务层出参{}", carouselIndexBOS);
        return carouselIndexBOS;
    }
}
