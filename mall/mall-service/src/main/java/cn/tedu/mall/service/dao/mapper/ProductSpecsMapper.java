package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.ProductSpecsPO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductSpecsMapper extends BaseMapper<ProductSpecsPO> {
    ProductSpecsVO selectProductSpecsById(Long id);
}
