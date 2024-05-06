package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.bo.BrandBO;
import cn.tedu.mall.service.pojo.vo.BrandVO;
import cn.tedu.mall.service.service.IBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "品牌模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;

    @ApiOperation("查询所有品牌功能")
    @GetMapping("/all")
    public JsonResult listForAll(){
        List<BrandBO> brandBOS = brandService.listForAll();
        List<BrandVO> brandVOS = PojoConvert.convertList(brandBOS, BrandVO.class);
        log.debug("查询所有品牌功能, 结果出参{}",brandVOS);
        return JsonResult.ok(brandVOS);
    }
}
