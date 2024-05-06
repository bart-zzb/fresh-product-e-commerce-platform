package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.CarouselPO;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselMapper extends BaseMapper<CarouselPO> {
    List<CarouselPO> selectListForIndex();
}
