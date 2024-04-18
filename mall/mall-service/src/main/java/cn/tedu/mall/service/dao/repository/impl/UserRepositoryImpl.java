package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.UserMapper;
import cn.tedu.mall.service.dao.repository.IUserRepository;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public CurrentPrincipal getCurrentPrincipalByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }
}
