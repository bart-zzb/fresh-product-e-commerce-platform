package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.service.dao.repository.ILiveRepository;
import cn.tedu.mall.service.pojo.vo.LiveVO;
import cn.tedu.mall.service.service.ILiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class LiveServiceImpl implements ILiveService {
    @Autowired
    private ILiveRepository liveRepository;

    @Override
    public List<LiveVO> listForIndex() {
        return liveRepository.listForIndex();
    }
}
