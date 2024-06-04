package cn.tedu.mall.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * mybatis-plus 用于新增或者插入数据时自动增添的字段值
 */
@Component
public class TimeMetaDataObjectHandler implements MetaObjectHandler {

    private static final String FIELD_CREATE_TIME = "createTime";

    private static final String FIELD_UPDATE_TIME = "modifiedTime";

    private static final String ORDER_NO = "orderNo";

    private static final String OUT_TRADE_NO = "outTradeNo";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME, now, metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
        this.setFieldValByName(ORDER_NO, UUID.randomUUID().toString(), metaObject);
        this.setFieldValByName(OUT_TRADE_NO, UUID.randomUUID().toString(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME, now, metaObject);
    }
}
