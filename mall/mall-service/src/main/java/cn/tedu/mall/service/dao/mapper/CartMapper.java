package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.CartPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<CartPO> {
    List<CartPO> selectCartByUserId(Long userId);

    CartPO selectCartByInfo(Long userId, Long productId, Long productSpecId);
}
