package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IBrandRepository;
import cn.tedu.mall.service.pojo.bo.BrandBO;
import cn.tedu.mall.service.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Primary
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<BrandBO> listForAll() {
        List<BrandBO> brandBOS = brandRepository.listForAll();
        log.debug("出参{}", brandBOS);
        return brandBOS;
    }
}
