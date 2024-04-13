package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.ProductAddDTO;
import cn.tedu.mall.service.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@Api(tags = "商品SPU模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @ApiOperation("增加商品SPU")
    @PostMapping("/add")
    public JsonResult addProduct(ProductAddDTO productAddDTO){
        productService.addProduct(productAddDTO);
        return JsonResult.ok();
    }
}
