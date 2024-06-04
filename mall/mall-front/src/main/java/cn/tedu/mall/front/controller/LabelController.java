package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.vo.LabelIndexVO;
import cn.tedu.mall.service.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "首页图标模块")
@Slf4j
@Validated
@RestController
@RequestMapping("/mall/label")
public class LabelController {
    @Autowired
    private ILabelService labelService;

    @ApiOperation("查询图标功能")
    @GetMapping("/index")
    public JsonResult listForIndex() {
        List<LabelIndexVO> list = labelService.listForIndex();
        return JsonResult.ok(list);
    }
}
