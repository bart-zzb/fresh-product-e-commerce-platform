package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.vo.LiveVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ILiveService {
    List<LiveVO> listForIndex();
}