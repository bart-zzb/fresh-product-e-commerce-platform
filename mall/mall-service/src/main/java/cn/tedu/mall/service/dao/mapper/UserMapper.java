package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.po.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
    CurrentPrincipal selectByUsernameAndPassword(String username, String password);
}
