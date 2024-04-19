package cn.tedu.mall.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TimeMetaDataObjectHandler implements MetaObjectHandler {

    private final String FIELD_CREATE_TIME = "createTime";

    private final String FIELD_UPDATE_TIME = "modifiedTime";

    private final String ORDER_NO = "orderNo";

    private final String OUT_TRADE_NO = "outTradeNo";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_CREATE_TIME,now,metaObject);
        this.setFieldValByName(FIELD_UPDATE_TIME,now,metaObject);
        this.setFieldValByName(ORDER_NO, UUID.randomUUID().toString(),metaObject);
        this.setFieldValByName(OUT_TRADE_NO, UUID.randomUUID().toString(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName(FIELD_UPDATE_TIME,now,metaObject);
    }
}
