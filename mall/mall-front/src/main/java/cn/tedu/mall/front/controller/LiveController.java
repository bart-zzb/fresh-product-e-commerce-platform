package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.LiveVO;
import cn.tedu.mall.service.service.ILiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "直播卡片模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/live")
public class LiveController {
    @Autowired
    private ILiveService liveService;

    @ApiOperation("查询直播卡片功能")
    @GetMapping("/index")
    public JsonResult listForIndex() {
        List<LiveVO> list = liveService.listForIndex();
        return JsonResult.ok(list);
    }
}
