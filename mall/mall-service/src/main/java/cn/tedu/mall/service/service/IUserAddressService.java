package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IUserAddressService {
    List<UserAddressBO> getUserAddressByUserId(Long userId);
}
