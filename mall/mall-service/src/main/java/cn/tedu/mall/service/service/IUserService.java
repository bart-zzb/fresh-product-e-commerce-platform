package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;

public interface IUserService {

    CurrentPrincipal getCurrentPrincipalByUsernameAndPassword(String username, String password);
}
