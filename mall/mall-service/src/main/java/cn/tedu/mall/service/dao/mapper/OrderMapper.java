package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<OrderPO> {
    List<OrderVO> selectOrderByUserId(Long userId);
}
