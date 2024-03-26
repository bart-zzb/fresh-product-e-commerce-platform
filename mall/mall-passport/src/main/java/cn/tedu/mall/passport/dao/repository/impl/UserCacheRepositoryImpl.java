package cn.tedu.mall.passport.dao.repository.impl;

import cn.tedu.mall.common.pojo.po.UserStatePO;
import cn.tedu.mall.passport.dao.repository.IUserCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserCacheRepositoryImpl implements IUserCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void saveUserState(UserStatePO userStatePO) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String userStateKey = "user_state_" + userStatePO.getUserId();
        String auth = userStatePO.getAuth();
        Integer enable = userStatePO.getEnable();
        Map<String,Object> map = new HashMap<>();
        map.put("auth",auth);
        map.put("enable",enable);
        hashOperations.putAll(userStateKey,map);
    }
}

