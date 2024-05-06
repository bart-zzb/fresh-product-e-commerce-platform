package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.BannerPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper extends BaseMapper<BannerPO> {
    List<BannerPO> selectListForIndex();
}
