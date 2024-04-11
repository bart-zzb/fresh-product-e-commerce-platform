package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.CarouselIndexVO;
import cn.tedu.mall.service.service.ICarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "首页轮播图模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/carousel")
public class CarouselController {
    @Autowired
    private ICarouselService carouselService;

    @ApiOperation("查询轮播图功能")
    @GetMapping("/index")
    public JsonResult listForIndex(){
        List<CarouselIndexVO> list = carouselService.listForIndex();
        return JsonResult.ok(list);
    }
}
