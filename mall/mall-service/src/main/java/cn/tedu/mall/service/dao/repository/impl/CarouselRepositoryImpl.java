package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.CarouselMapper;
import cn.tedu.mall.service.dao.repository.ICarouselRepository;
import cn.tedu.mall.service.pojo.bo.CarouselIndexBO;
import cn.tedu.mall.service.pojo.po.CarouselPO;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Primary
@Repository
public class CarouselRepositoryImpl implements ICarouselRepository {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<CarouselIndexBO> listForIndex() {
        List<CarouselPO> carouselPOS = carouselMapper.selectListForIndex();
        List<CarouselIndexBO> carouselIndexBOS = PojoConvert.convertList(carouselPOS, CarouselIndexBO.class);
        log.debug("查询查询轮播图功能,仓储层出参{}", carouselIndexBOS);
        return carouselIndexBOS;
    }
}
