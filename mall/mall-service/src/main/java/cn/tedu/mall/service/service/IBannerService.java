package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.BannerIndexVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IBannerService {
    List<BannerIndexVO> listForIndex();
}
