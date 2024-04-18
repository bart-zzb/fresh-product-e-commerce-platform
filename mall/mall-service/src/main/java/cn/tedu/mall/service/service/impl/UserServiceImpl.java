package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IUserRepository;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
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
    public CurrentPrincipal getCurrentPrincipalByUsernameAndPassword(String username, String password) {
        return userRepository.getCurrentPrincipalByUsernameAndPassword(username, password);
    }
}
