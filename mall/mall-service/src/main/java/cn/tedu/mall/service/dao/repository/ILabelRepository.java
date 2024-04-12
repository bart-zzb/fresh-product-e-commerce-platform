package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.vo.LabelIndexVO;

import java.util.List;

public interface ILabelRepository {
    List<LabelIndexVO> listForIndex();
}
