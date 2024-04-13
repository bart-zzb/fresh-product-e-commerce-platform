package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.ProductPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductPO> {
}
