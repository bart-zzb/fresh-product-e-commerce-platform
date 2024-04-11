package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.ICarouselRepository;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import cn.tedu.mall.service.service.ICarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CarouselServiceImpl implements ICarouselService {
    @Autowired
    private ICarouselRepository carouselRepository;

    @Override
    public List<CarouselIndexVO> listForIndex() {
        return carouselRepository.listForIndex();
    }
}
