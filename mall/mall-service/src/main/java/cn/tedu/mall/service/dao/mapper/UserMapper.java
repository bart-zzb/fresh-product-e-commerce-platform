package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.UserPO;
import cn.tedu.mall.service.pojo.bo.UserBO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
    UserPO selectByUsername(String username);
}
