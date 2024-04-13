package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.BrandPO;
import cn.tedu.mall.service.pojo.vo.BrandVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper extends BaseMapper<BrandPO> {
    List<BrandVO> selectListForAll();
}
