package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.UserBO;

public interface IUserRepository {
    UserBO getCurrentPrincipalByUsername(String username);

    void saveUserByUsernameAndPassword(String username, String password);
}
