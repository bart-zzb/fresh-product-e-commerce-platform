package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.ProductAddDTO;
import cn.tedu.mall.service.pojo.vo.ProductVO;
import cn.tedu.mall.service.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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
    public JsonResult addProduct(@Validated ProductAddDTO productAddDTO) {
        productService.addProduct(productAddDTO);
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除商品SPU")
    @PostMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    public JsonResult deleteProductById(@PathVariable @NotNull Long id) {
        productService.deleteProductById(id);
        return JsonResult.ok();
    }

    @ApiOperation("根据id查询商品SPU")
    @GetMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    public JsonResult getProductById(@PathVariable @NotNull Long id) {
        ProductVO productVO = productService.getProductById(id);
        return JsonResult.ok(productVO);
    }
}
