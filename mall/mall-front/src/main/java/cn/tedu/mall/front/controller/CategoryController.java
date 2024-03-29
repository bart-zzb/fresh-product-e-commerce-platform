package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.CategoryVO;
import cn.tedu.mall.service.service.ICategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "商品类别")
@Slf4j
@RestController
@RequestMapping("mall/Category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping ("/{level}")
    public JsonResult getCategoryByLevel(@PathVariable Integer level){
        List<CategoryVO> list = categoryService.listCategoryByLevel(level);
        log.debug("商品类别列表:"+ list);
        return JsonResult.success(list);
    }
}
