package cn.tedu.mall.passport.dao.repository;

import cn.tedu.mall.common.pojo.po.UserStatePO;

public interface IUserCacheRepository {
    void saveUserState(UserStatePO userStatePO);
}
