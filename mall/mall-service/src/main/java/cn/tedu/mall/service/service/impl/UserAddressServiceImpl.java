package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.IUserAddressRepository;
import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import cn.tedu.mall.service.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class UserAddressServiceImpl implements IUserAddressService {
    @Autowired
    private IUserAddressRepository userAddressRepository;

    @Override
    public List<UserAddressBO> getUserAddressByUserId(Long userId) {
        return userAddressRepository.getUserAddressByUserId(userId);
    }
}
