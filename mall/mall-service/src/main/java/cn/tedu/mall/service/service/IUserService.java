package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface IUserService {

    CurrentPrincipal getCurrentPrincipalByUsernameAndPassword(String username, String password);
}
