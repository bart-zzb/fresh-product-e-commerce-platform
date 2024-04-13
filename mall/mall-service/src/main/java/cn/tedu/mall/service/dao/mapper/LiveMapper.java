package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.LivePO;
import cn.tedu.mall.service.pojo.vo.LiveVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LiveMapper extends BaseMapper<LivePO> {
    List<LiveVO> selectListForIndex();
}
