package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.constant.UserConstants;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.UserMapper;
import cn.tedu.mall.service.dao.repository.IUserRepository;
import cn.tedu.mall.service.pojo.po.UserPO;
import cn.tedu.mall.service.pojo.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBO getCurrentPrincipalByUsername(String username) {
        UserPO userPO = userMapper.selectByUsername(username);
        if (userPO != null) {
            return PojoConvert.convert(userPO, UserBO.class);
        }
        return null;
    }

    @Override
    public void saveUserByUsernameAndPassword(String username, String password) {
        UserPO userPO = new UserPO();
        userPO.setUsername(username);
        userPO.setPassword(password);
        userPO.setUserType(UserConstants.INDIVIDUAL.getValue());
        userMapper.insert(userPO);
    }
}
