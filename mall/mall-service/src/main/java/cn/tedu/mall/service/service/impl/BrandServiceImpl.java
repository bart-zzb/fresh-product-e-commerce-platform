package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IBrandRepository;
import cn.tedu.mall.service.pojo.vo.BrandVO;
import cn.tedu.mall.service.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public List<BrandVO> listForAll() {
        return brandRepository.listForAll();
    }
}
