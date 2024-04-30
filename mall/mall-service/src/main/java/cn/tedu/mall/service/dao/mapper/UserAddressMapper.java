package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.UserAddressPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper extends BaseMapper<UserAddressPO> {
    int updateUserAddressByUserIdAndId(Long userId, Long id);

    int updateUserAddress2NotDefault(Long userId);

    UserAddressPO selectDefaultAddressByUserId(Long userId);

    UserAddressPO getAddressById(Long id);

    int updateAddress(UserAddressPO userAddressPO);
}
