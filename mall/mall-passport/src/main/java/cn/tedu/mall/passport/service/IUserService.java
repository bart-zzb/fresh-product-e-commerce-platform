package cn.tedu.mall.passport.service;

import cn.tedu.mall.passport.pojo.dto.UserLoginInfoDTO;
import cn.tedu.mall.passport.pojo.vo.UserLoginResultVO;

public interface IUserService {
    UserLoginResultVO login(UserLoginInfoDTO userLoginInfoDTO);
}
