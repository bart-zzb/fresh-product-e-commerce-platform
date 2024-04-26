package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IUserRepository;
import cn.tedu.mall.service.pojo.bo.UserBO;
import cn.tedu.mall.service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserBO getUserByUsername(String username) {
        return userRepository.getCurrentPrincipalByUsername(username);
    }

    @Override
    public void saveUserByUsernameAndPassword(String username, String password) {
        userRepository.saveUserByUsernameAndPassword(username, password);
    }
}
