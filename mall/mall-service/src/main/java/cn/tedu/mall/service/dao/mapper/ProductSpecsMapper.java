package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.bo.ProductSpecsBO;
import cn.tedu.mall.service.pojo.po.ProductSpecsPO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecsMapper extends BaseMapper<ProductSpecsPO> {
    ProductSpecsVO selectProductSpecsById(Long id);

    List<ProductSpecsVO> selectProductSpecsByCategoryId(Long id);

    ProductSpecsBO selectProductIdByProductSpecsId(Long id);
}
