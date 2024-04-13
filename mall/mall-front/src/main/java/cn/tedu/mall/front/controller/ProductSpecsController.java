package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IProductSpecsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Api(tags = "商品SKU模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/product_specs")
public class ProductSpecsController {
    @Autowired
    private IProductSpecsService productSpecsService;

    @ApiOperation("根据id查询商品SKU")
    @GetMapping("/{id}")
    public JsonResult getProductSpecsById(@PathVariable @NotNull Long id){
        ProductSpecsVO productSpecsVO = productSpecsService.getProductSpecsById(id);
        return JsonResult.ok(productSpecsVO);
    }
}
