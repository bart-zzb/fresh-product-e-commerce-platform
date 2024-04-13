package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.LiveMapper;
import cn.tedu.mall.service.dao.repository.ILiveRepository;
import cn.tedu.mall.service.pojo.vo.LiveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class LiveRepository implements ILiveRepository {
    @Autowired
    private LiveMapper liveMapper;

    @Override
    public List<LiveVO> listForIndex() {
        return liveMapper.selectListForIndex();
    }
}
