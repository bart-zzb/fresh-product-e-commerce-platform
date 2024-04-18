package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;

public interface IUserRepository {
    CurrentPrincipal getCurrentPrincipalByUsernameAndPassword(String username, String password);
}
