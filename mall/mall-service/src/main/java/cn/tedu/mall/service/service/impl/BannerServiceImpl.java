package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.service.dao.repository.IBannerRepository;
import cn.tedu.mall.service.pojo.bo.BannerIndexBO;
import cn.tedu.mall.service.service.IBannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Primary
@Service
public class BannerServiceImpl implements IBannerService {
    @Autowired
    private IBannerRepository bannerRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<BannerIndexBO> listForIndex() throws JsonProcessingException {
        List<BannerIndexBO> BannerIndexBOS = new ArrayList<>();
        String key = RedisConstants.KEY_PREFIX_BANNER;
        Boolean b = stringRedisTemplate.hasKey(key);
        //判断key是否存在
        if (Boolean.TRUE.equals(b)) {
            List<String> range = stringRedisTemplate.opsForList().range(key, 0, -1);
            //判断list长度是否为空,长度不能为0
            if(range!=null&& !range.isEmpty()){
                for (String s : range) {
                    BannerIndexBOS.add(objectMapper.readValue(s, BannerIndexBO.class));
                }
            }
            log.debug("从缓存中获取Banner数据{}",BannerIndexBOS);
            return BannerIndexBOS;
        }

        return loadBanner2Redis(key);
    }

    @Override
    public void initBanner() throws JsonProcessingException {
        String key = RedisConstants.KEY_PREFIX_BANNER;
        stringRedisTemplate.delete(key);
        loadBanner2Redis(key);
    }

    private List<BannerIndexBO> loadBanner2Redis(String key) throws JsonProcessingException {
        List<BannerIndexBO> bannerIndexBOS = bannerRepository.listForIndex();
        log.debug("从数据库获取banner信息, 并加载到Redis中{}",bannerIndexBOS);
        List<String> bannerIndexBOSStr = new ArrayList<>();
        for (BannerIndexBO bannerIndexBO : bannerIndexBOS) {
            bannerIndexBOSStr.add(objectMapper.writeValueAsString(bannerIndexBO));
        }
        stringRedisTemplate.opsForList().leftPushAll(key, bannerIndexBOSStr);
        return bannerIndexBOS;
    }
}
