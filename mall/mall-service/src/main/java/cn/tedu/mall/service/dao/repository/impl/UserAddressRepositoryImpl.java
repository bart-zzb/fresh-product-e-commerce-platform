package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.UserAddressMapper;
import cn.tedu.mall.service.dao.repository.IUserAddressRepository;
import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import cn.tedu.mall.service.pojo.po.UserAddressPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Primary
@Repository
public class UserAddressRepositoryImpl implements IUserAddressRepository {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddressBO> getUserAddressByUserId(Long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tb_user_id", userId);
        List<UserAddressPO> userAddressPOS = userAddressMapper.selectList(queryWrapper);
        return PojoConvert.convertList(userAddressPOS, UserAddressBO.class);
    }

    @Override
    public int updateUserAddressIsDefaultById(Long userId, Long id) {
        return userAddressMapper.updateUserAddressByUserIdAndId(userId, id);
    }

    @Override
    public int updateUserAddress2NotDefault(Long userId) {
        return userAddressMapper.updateUserAddress2NotDefault(userId);
    }

    @Override
    public UserAddressBO getDefaultAddressByUserId(Long userId) {
        UserAddressPO userAddressPO = userAddressMapper.selectDefaultAddressByUserId(userId);
        if(userAddressPO==null){
            return null;
        }else{
            return PojoConvert.convert(userAddressPO, UserAddressBO.class);
        }
    }

    @Override
    public UserAddressBO getAddressById(Long userId, Long id) {
        UserAddressPO userAddressPO = userAddressMapper.getAddressById(id);
        if(userAddressPO==null){
            return null;
        }else{
            return PojoConvert.convert(userAddressPO, UserAddressBO.class);
        }
    }

    @Override
    public int updateAddress(UserAddressBO userAddressBO) {
        UserAddressPO userAddressPO = PojoConvert.convert(userAddressBO, UserAddressPO.class);
        userAddressPO.setModifiedTime(LocalDateTime.now());
        return userAddressMapper.updateAddress(userAddressPO);
    }

    @Override
    public int insertAddress(UserAddressBO userAddressBO) {
        UserAddressPO userAddressPO = PojoConvert.convert(userAddressBO, UserAddressPO.class);
        return userAddressMapper.insert(userAddressPO);
    }

    @Override
    public int deleteAddressById(Long id) {
        return userAddressMapper.deleteById(id);
    }
}
