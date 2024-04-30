package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.UserAddressBO;

import java.util.List;

public interface IUserAddressRepository {
    List<UserAddressBO> getUserAddressByUserId(Long userId);

    int updateUserAddressIsDefaultById(Long userId, Long id);

    int updateUserAddress2NotDefault(Long userId);

    UserAddressBO getDefaultAddressByUserId(Long userId);

    UserAddressBO getAddressById(Long userId, Long id);

    int updateAddress(UserAddressBO userAddressBO);

    int insertAddress(UserAddressBO userAddressBO);

    int deleteAddressById(Long id);
}
