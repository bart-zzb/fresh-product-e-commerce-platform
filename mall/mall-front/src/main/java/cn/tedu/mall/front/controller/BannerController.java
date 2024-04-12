package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.BannerIndexVO;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import cn.tedu.mall.service.service.IBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "首页横幅模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/banner")
public class BannerController {
    @Autowired
    private IBannerService bannerService;

    @ApiOperation("查询横幅功能")
    @GetMapping("/index")
    public JsonResult listForIndex(){
        List<BannerIndexVO> list = bannerService.listForIndex();
        return JsonResult.ok(list);
    }
    //TODO 增加, 修改, 删除横幅
}
