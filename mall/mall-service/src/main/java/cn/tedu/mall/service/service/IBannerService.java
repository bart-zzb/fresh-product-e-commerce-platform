package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.BannerIndexBO;
import cn.tedu.mall.service.pojo.vo.BannerIndexVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IBannerService {
    List<BannerIndexBO> listForIndex() throws JsonProcessingException;

    void initBanner() throws JsonProcessingException;
}
