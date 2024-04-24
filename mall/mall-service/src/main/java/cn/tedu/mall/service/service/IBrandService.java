package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.BrandVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IBrandService {
    List<BrandVO> listForAll();
}
