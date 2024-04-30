package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IUserAddressService {
    List<UserAddressBO> getUserAddressByUserId(Long userId);

    void changeUserAddressIsDefaultById(Long userId, Long id);

    UserAddressBO getDefaultAddressByUserId(Long id);

    UserAddressBO getAddressById(Long userId, Long id);

    int updateAddress(UserAddressBO userAddressBO);

    int addAddress(UserAddressBO userAddressBO);

    int deleteAddressById(Long id);
}
