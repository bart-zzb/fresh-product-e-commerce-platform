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

    @Override
    public void changeUserAddressIsDefaultById(Long userId, Long id) {
        //修改已默认地址为未默认状态
        userAddressRepository.updateUserAddress2NotDefault(userId);
        userAddressRepository.updateUserAddressIsDefaultById(userId, id);
    }

    @Override
    public UserAddressBO getDefaultAddressByUserId(Long id) {
        return userAddressRepository.getDefaultAddressByUserId(id);
    }

    @Override
    public UserAddressBO getAddressById(Long userId, Long id) {
        return userAddressRepository.getAddressById(userId, id);
    }

    @Override
    public int updateAddress(UserAddressBO userAddressBO) {
        return userAddressRepository.updateAddress(userAddressBO);
    }

    @Override
    public int addAddress(UserAddressBO userAddressBO) {
        return userAddressRepository.insertAddress(userAddressBO);
    }

    @Override
    public int deleteAddressById(Long id) {
        return userAddressRepository.deleteAddressById(id);
    }
}
