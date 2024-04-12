package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.ILabelRepository;
import cn.tedu.mall.service.pojo.vo.LabelIndexVO;
import cn.tedu.mall.service.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class LabelServiceImpl implements ILabelService {
    @Autowired
    private ILabelRepository labelRepository;

    @Override
    public List<LabelIndexVO> listForIndex() {
        return labelRepository.listForIndex();
    }
}
