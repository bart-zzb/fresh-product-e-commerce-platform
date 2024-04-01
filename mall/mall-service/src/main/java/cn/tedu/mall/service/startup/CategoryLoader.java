package cn.tedu.mall.service.startup;

import cn.tedu.mall.service.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 缓存预热
 * 类别树加载器
 * 从数据库加载类别到redis
 */
@Slf4j
@Component
public class CategoryLoader implements ApplicationRunner {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("ApplicationRunner 开始加载类别数据到redis");
        // 业务流程
        // 1 从数据库查出来
        // 2 放到redis
        categoryService.initCategory();
        log.debug("ApplicationRunner 加载类别数据到redis 成功");
    }
}
