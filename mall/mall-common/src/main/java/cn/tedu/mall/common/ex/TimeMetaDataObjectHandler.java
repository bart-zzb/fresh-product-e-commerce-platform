package cn.tedu.mall.common.ex;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeMetaDataObjectHandler implements MetaObjectHandler {

    private final String FIELD_CREATE_TIME = "createTime";

    private final String FIELD_UPDATE_TIME = "modifiedTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME,now,metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME,now,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME,now,metaObject);
    }
}
