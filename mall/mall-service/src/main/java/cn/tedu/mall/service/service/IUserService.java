package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.UserBO;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public interface IUserService {

    UserBO getUserByUsername(String username);

    void saveUserByUsernameAndPassword(String username, String password);
}
