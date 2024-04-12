package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.LabelPO;
import cn.tedu.mall.service.pojo.vo.LabelIndexVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LabelMapper extends BaseMapper<LabelPO> {
    List<LabelIndexVO> selectListForIndex();
}
