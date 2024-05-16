package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.CategoryAddDTO;
import cn.tedu.mall.service.pojo.dto.CategoryUpdateDTO;
import cn.tedu.mall.service.pojo.vo.CategoryTreeVO;
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

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "商品类别模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    /**
     * 增加类别名称
     * @param categoryAddDTO 类别
     * @return JsonResult
     */
    @ApiOperation("增加商品类别")
    @PostMapping("/add")
    public JsonResult addCategory(@Validated CategoryAddDTO categoryAddDTO){
        categoryService.addCategory(categoryAddDTO);
        return JsonResult.ok();
    }

    /**
     * 删除类别名称
     * @param id 类别id
     * @return JsonResult
     */
    @ApiOperation("删除商品类别")
    @PostMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商品id",required = true,dataType = "Long"),
    })
    public JsonResult deleteCategoryById(@PathVariable @NotNull(message = "商品id不能为空") Long id){
        categoryService.deleteCategoryById(id);
        return JsonResult.ok();
    }

    /**
     * 根据类别层级查询所有类别
     * @param level 层级
     * @return JsonResult(List<CategoryVO>)
     */
    @ApiOperation("根据商品类别层级查询所有商品类别")
    @GetMapping ("/level/{level}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "level",value = "分类层级",required = true,dataType = "Integer"),
    })
    public JsonResult getCategoryByLevel(@PathVariable @Range(min = 1, max = 2, message = "分类层级必须是1或2之间") Integer level){
        List<CategoryVO> list = categoryService.listCategoryByLevel(level);
        log.debug("商品类别列表:{}",list);
        return JsonResult.ok(list);
    }

    /**
     * 查询商品类别树
     * @return List<CategoryTreeVO>
     */
    @ApiOperation("查询商品类别树")
    @GetMapping ("/tree")
    public JsonResult treeCategory(){
        List<CategoryTreeVO> tree = categoryService.treeCategory();
        return JsonResult.ok(tree);
    }

    /**
     * 更新类别名称
     * @param categoryUpdateDTO 商品类别更新
     * @return JsonResult
     */
    @ApiOperation("更新商品类别")
    @PostMapping("/update")
    public JsonResult updateCategoryById(@Validated CategoryUpdateDTO categoryUpdateDTO){
        categoryService.updateCategory(categoryUpdateDTO);
        return JsonResult.ok();
    }
}