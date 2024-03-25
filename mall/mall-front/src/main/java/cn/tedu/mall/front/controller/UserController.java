package cn.tedu.mall.front.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户模块")
@Validated
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
}
