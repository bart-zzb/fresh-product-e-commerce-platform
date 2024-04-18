package cn.tedu.mall.service.pojo.authentication;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentPrincipal implements Serializable {
    /**
     * 当前用户ID
     */
    Long id;

    /**
     * 当前用户名
     */
    String username;
}
