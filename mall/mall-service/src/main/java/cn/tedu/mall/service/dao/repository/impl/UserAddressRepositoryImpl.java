package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.UserAddressMapper;
import cn.tedu.mall.service.dao.repository.IUserAddressRepository;
import cn.tedu.mall.service.pojo.bo.UserAddressBO;
import cn.tedu.mall.service.pojo.po.UserAddressPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
