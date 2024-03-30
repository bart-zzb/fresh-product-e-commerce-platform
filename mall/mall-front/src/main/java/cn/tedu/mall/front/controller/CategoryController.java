package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import cn.tedu.mall.service.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品类别")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/Category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 根据类别层级查询所有类别
     * @param level 层级
     * @return List<CategoryVO>
     */
    @ApiOperation("根据商品类别层级查询所有商品类别")
    @GetMapping ("/{level}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level",value = "分类层级",required = true,dataType = "Integer"),
    })
    public JsonResult getCategoryByLevel(@PathVariable @Range(min = 1, max = 2, message = "分类层级必须是1或2之间") Integer level){
        List<CategoryVO> list = categoryService.listCategoryByLevel(level);
        log.debug("商品类别列表:"+ list);
        return JsonResult.success(list);
    }

    @ApiOperation("增加类别")
    @PostMapping("/add")
    public JsonResult addCategory(CategoryAddDTO categoryAddDTO){
        categoryService.addCategory(categoryAddDTO);
        return JsonResult.success();
    }
}
