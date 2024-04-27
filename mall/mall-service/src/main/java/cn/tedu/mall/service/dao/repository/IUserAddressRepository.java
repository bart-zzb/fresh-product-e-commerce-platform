package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.UserAddressBO;

import java.util.List;

public interface IUserAddressRepository {
    List<UserAddressBO> getUserAddressByUserId(Long userId);
}
