package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.BrandVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IBrandService {
    List<BrandVO> listForAll();
}
