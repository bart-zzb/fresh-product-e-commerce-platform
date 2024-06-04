package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.common.web.PageData;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.dto.ProductSpecDeleteDTO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsTreeVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IProductSpecsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    public JsonResult getProductSpecsById(@PathVariable @NotNull Long id) {
        ProductSpecsVO productSpecsVO = productSpecsService.getProductSpecsById(id);
        return JsonResult.ok(productSpecsVO);
    }

    @ApiOperation("根据商品分类id查询所有商品SKU")
    @GetMapping("/category/{id}")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "商品分类id", required = true, example = "8", dataType = "Long"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页面展示数量", required = true, dataType = "Integer")
    })
    public JsonResult getProductSpecsByCategoryId(@PathVariable @NotNull Long id, @Range(min = 1) @RequestParam Integer pageNum, @RequestParam @Range(min = 1) Integer pageSize) {
        PageData<ProductSpecsVO> pageData = productSpecsService.getProductSpecsByCategoryId(id, pageNum, pageSize);
        log.debug("pageData:{}", pageData);
        return JsonResult.ok(pageData);
    }

    @ApiOperation("根据商品分类树包含商品SKU")
    @GetMapping("/tree")
    public JsonResult getProductSpecsTree() throws JsonProcessingException, InterruptedException {
        List<ProductSpecsTreeVO> list = productSpecsService.getProductSpecsTree();
        return JsonResult.ok(list);
    }

    @ApiOperation("删除商品的库存")
    @PostMapping("/modify")
    public JsonResult deleteProductSpecsAmount(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated @RequestBody List<ProductSpecDeleteDTO> productSpecDeleteDTOS) {
        log.debug("入参{}", productSpecDeleteDTOS);
        productSpecsService.deleteProductSpecsAmount(currentPrincipal.getId(), productSpecDeleteDTOS);
        return JsonResult.ok();
    }
}
