package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.LabelMapper;
import cn.tedu.mall.service.dao.repository.ILabelRepository;
import cn.tedu.mall.service.pojo.vo.LabelIndexVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class LabelRepositoryImpl implements ILabelRepository {
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<LabelIndexVO> listForIndex() {
        return labelMapper.selectListForIndex();
    }
}
