package cn.tedu.mall.passport.service.impl;

import cn.tedu.mall.common.pojo.po.UserStatePO;
import cn.tedu.mall.common.util.JwtUtils;
import cn.tedu.mall.passport.dao.repository.IUserCacheRepository;
import cn.tedu.mall.passport.pojo.dto.UserLoginInfoDTO;
import cn.tedu.mall.passport.pojo.vo.UserLoginResultVO;
import cn.tedu.mall.passport.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private String secretKey = "fNesMDkqrJFdsfDSwAbFLJ8SnsHJ438AF72D73aKJSmfdsafdLKKAFKDSJ";

    @Autowired
    private IUserCacheRepository userCacheRepository;

    /**
     * 1 验证用户名
     * 2 验证密码
     * 3 判断用户状态(有可能用户别管理员给禁用 拉黑) 可选
     * 4 记录下登录日志 (可选)
     * 4 生成token
     * @param userLoginInfoDTO
     * @return
     */
    @Override
    public UserLoginResultVO login(UserLoginInfoDTO userLoginInfoDTO) {
        //用户名
        String username = userLoginInfoDTO.getUsername();
        //1 todo 验证用户名 通过数据库来验证 我们讲过 不带大家演示
        //密码
        String password = userLoginInfoDTO.getPassword();
        // 2 todo 验证密码 通过数据库查询 不做 我们讲过 不带大家演示
        //伪造的,应该通过用户名去数据库查询(用户名是唯一的)
        // 3 判断用户状态
        Long userId = 1L; //区分唯一用户唯一标识的id
        // 可选的 记录用户登录日志,登录次数 可选的业务逻辑
        //3 生成token 令牌 工卡
        Map<String, Object> claims = new HashMap<>();
        //id是唯一的,后面的系统,购物车就可以通过id区分是谁,
        claims.put("id", userId);
        claims.put("name", username);
        claims.put("liveTime",System.currentTimeMillis());//当前时间
        //这个map可以放什么? 什么都能放,放的多,会导致token变长
        //数据越多,加密越慢,解密也越慢.
        //每次访问别的系统,都需要带着,浪费网络带宽,和存储空间
        String jwt = JwtUtils.generate(claims, secretKey);
        log.debug("生成用户的JWT数据：{}", jwt);
        //获取权限信息 存入到redis里
        // 1 获取 5表连查
        // 2 存储 用什么样的数据类型?
        //   不合理   list  key user_stat_+UserId(1)  value 权限列表
        //   Hash  key  user_stat_+UserId(1) hashKey
        // Map<String,Map<String,value>>
        // 1 一个人一个大map  分布式的概念
        //    数据分散 结合 用户量 5000W人  结合业务 200个管理员
        // 2 所有人共有一个大map 每个人是个小map  2
        // 实现业务,节约成本,考虑扩展性
        // 一个人一个大map  分布式的概念
        // key user_status_123 ,
        // value
        //      auth 权限列表
        //      status 用户状态
        //      time :失效时间
        UserStatePO userStatePO = new UserStatePO();
        userStatePO.setUserId(userId);
        userStatePO.setEnable(1);
        String auth  = "[{authority:'/article/add'},{authority:'/article/detail'}]";
        userStatePO.setAuth(auth); //从数据库5表查出的权限列表
        userCacheRepository.saveUserState(userStatePO);

        //定义登录返回数据
        UserLoginResultVO userLoginResultVO = new UserLoginResultVO();
        //设置上面生成好的token
        userLoginResultVO.setToken(jwt);
        userLoginResultVO.setUsername(username);
        userLoginResultVO.setId(userId);
        //返回给客户端
        return userLoginResultVO;
    }
}

